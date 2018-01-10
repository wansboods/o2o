package cn.wansboods.o2o.enums;

public enum ShopStateEmum {
    CHECK( 0, "审核中"),OFFLINE( -1, "非法店铺" ), SUCCESS( 1, "操作成功" ),
    PASS( 2, "审核通过" ), INNER_ERROR( -1001, "内部系统错误"),
    NULL_SHOPID( -1002, "shopId为空"), NULL_SHOP( -1003, "shop信息为空" );

    private int state;
    private String stateInfo;

    private ShopStateEmum( int state, String sateInfo ){
        this.state = state;
        this.stateInfo = sateInfo;
    }

    public static ShopStateEmum stateOf( int state ){
        for( ShopStateEmum stateEmum : values() ){
            if( stateEmum.getState() == state ){
                return stateEmum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
