package cn.wansboods.o2o.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class,method = "query",
            args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class })})

public class DynamicDataSourceInterceptor implements Interceptor {
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static Logger logger = LoggerFactory.getLogger( DynamicDataSourceInterceptor.class );
    /**
     * 主要拦截
     * @param invocation
     * @return
     * @throws Throwable
     */

    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchroniztionActive = TransactionSynchronizationManager.isActualTransactionActive();
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        String lookupKey =  DynamicDataSourceHolder.DB_MASTER;
        if( synchroniztionActive != true ){
            //读方法
            if( ms.getSqlCommandType().equals( SqlCommandType.SELECT )){
                //selectKey 未自增长id查询主键( SELECT LAST_INSERT_ID())方法, 使用主库
                if( ms.getId ().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX ) ){
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                }else{
                    BoundSql boundSql = ms.getSqlSource().getBoundSql( objects[1] );
                    String sql = boundSql.getSql().toLowerCase( Locale.CHINA ).replace("\\t\\n\\r"," " );
                    if( sql.matches( REGEX ) != true ){
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }else{
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    }
                }
            }
        }else{
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }

        logger.debug( "设置的方法[{}], use[{}] Strategy, SqlCommanType[{}]", ms.getId(), lookupKey,
                ms.getSqlCommandType().name() );
        DynamicDataSourceHolder.setDbType( lookupKey );
        return invocation.proceed();
    }

    /**
     *  返回代理对象
     * @param target
     * @return
     */
    public Object plugin(Object target ) {
        // Executor 用来支持mybatis 增删改查方法
        if( target instanceof Executor ) return Plugin.wrap(target,this);
        return target;
    }

    /**
     * 相关设置
     * @param properties
     */
    public void setProperties(Properties properties) {

    }
}
