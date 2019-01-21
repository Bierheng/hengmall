package com.hengmall.goods.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hengmall.goods.dao.ActivityModuleDao;
import com.hengmall.goods.dao.AdsDao;
import com.hengmall.goods.dao.ApplyRefundDao;
import com.hengmall.goods.dao.BrandsDao;
import com.hengmall.goods.dao.CombineOrderDao;
import com.hengmall.goods.dao.CombineSaleDao;
import com.hengmall.goods.dao.FlashSaleDao;
import com.hengmall.goods.dao.FlashSaleTimelinessDao;
import com.hengmall.goods.dao.GroupProductDao;
import com.hengmall.goods.dao.MsgCenterDao;
import com.hengmall.goods.dao.PAddressDao;
import com.hengmall.goods.dao.PlatformPlateDao;
import com.hengmall.goods.dao.PlatformTagDao;
import com.hengmall.goods.dao.RelCarouselDao;
import com.hengmall.goods.dao.RelCollectionDao;
import com.hengmall.goods.dao.RelCouponDao;
import com.hengmall.goods.dao.RelFootprintDao;
import com.hengmall.goods.dao.RelIndexProductDao;
import com.hengmall.goods.dao.RelProductDetailDao;
import com.hengmall.goods.dao.RelRecommendDao;
import com.hengmall.goods.dao.SAppraiseDao;
import com.hengmall.goods.dao.SCategoryDao;
import com.hengmall.goods.dao.SCouponDao;
import com.hengmall.goods.dao.SOrderDao;
import com.hengmall.goods.dao.SOrderExtraDao;
import com.hengmall.goods.dao.SProductDao;
import com.hengmall.goods.dao.SResourcesDao;
import com.hengmall.goods.dao.SShoppingcartDao;
import com.hengmall.goods.dao.ShopsCommendDao;
import com.hengmall.goods.dao.ShopsDao;
import com.hengmall.goods.dao.ShopsRightDao;
import com.hengmall.goods.dao.SuccessKilledDao;
import com.hengmall.goods.dao.SystemConfigDao;
import com.hengmall.goods.dao.TbTopicDao;
import com.hengmall.goods.dao.TbUserDao;
import com.hengmall.goods.dao.TigLibraryDao;
import com.hengmall.goods.dao.UsersDao;
import com.hengmall.goods.dao.WalletDetailsDao;
import com.hengmall.goods.exceptions.AuthException;
import com.hengmall.goods.model.ApplyRefundEntity;
import com.hengmall.goods.model.CombineOrderEntity;
import com.hengmall.goods.model.CombineSaleEntity;
import com.hengmall.goods.model.FlashSaleEntity;
import com.hengmall.goods.model.FlashSaleTimelinessEntity;
import com.hengmall.goods.model.MsgCenterEntity;
import com.hengmall.goods.model.PAddressEntity;
import com.hengmall.goods.model.PlatformInfoEntity;
import com.hengmall.goods.model.PlatformInfoEntity2;
import com.hengmall.goods.model.PlatformInfoReq;
import com.hengmall.goods.model.PlatformPlateEntity;
import com.hengmall.goods.model.PlatformTagEntity;
import com.hengmall.goods.model.ProductReq;
import com.hengmall.goods.model.RelCarouselEntity;
import com.hengmall.goods.model.RelCollectionEntity;
import com.hengmall.goods.model.RelProductDetailEntity;
import com.hengmall.goods.model.SAppraiseEntity;
import com.hengmall.goods.model.SCategoryEntity;
import com.hengmall.goods.model.SOrderEntity;
import com.hengmall.goods.model.SOrderExtraEntity;
import com.hengmall.goods.model.SProductEntity;
import com.hengmall.goods.model.SResourcesEntity;
import com.hengmall.goods.model.SShoppingcartEntity;
import com.hengmall.goods.model.ShopsCommend;
import com.hengmall.goods.model.ShopsInfo;
import com.hengmall.goods.model.ShopsLocation;
import com.hengmall.goods.model.ShopsOrderListEntity;
import com.hengmall.goods.model.ShopsRight;
import com.hengmall.goods.model.SuccessKilledEntity;
import com.hengmall.goods.model.TbTopic;
import com.hengmall.goods.model.UsersEntity;
import com.hengmall.goods.model.WalletDetailsEntity;
import com.hengmall.goods.model.api.AddCartReq;
import com.hengmall.goods.model.api.Ajax;
import com.hengmall.goods.model.api.ApiRequest;
import com.hengmall.goods.model.api.AppraiseListReq;
import com.hengmall.goods.model.api.AppraiseReq;
import com.hengmall.goods.model.api.CategoryOrderReq;
import com.hengmall.goods.model.api.CollectionListResp;
import com.hengmall.goods.model.api.CombineDetailsReq;
import com.hengmall.goods.model.api.CombineDetailsResp;
import com.hengmall.goods.model.api.CombineOrderList;
import com.hengmall.goods.model.api.CombineOrderReq;
import com.hengmall.goods.model.api.CombineOrderResp;
import com.hengmall.goods.model.api.CommodityReq;
import com.hengmall.goods.model.api.ConfirmDelivery;
import com.hengmall.goods.model.api.CouponPar;
import com.hengmall.goods.model.api.CouponReq;
import com.hengmall.goods.model.api.DefaultAddressReq;
import com.hengmall.goods.model.api.DelAddressReq;
import com.hengmall.goods.model.api.DelCollection;
import com.hengmall.goods.model.api.DelCoupon;
import com.hengmall.goods.model.api.DelFile;
import com.hengmall.goods.model.api.DelMsgReq;
import com.hengmall.goods.model.api.DelMyFootprint;
import com.hengmall.goods.model.api.DelShoppingCarts;
import com.hengmall.goods.model.api.EditAddressReq;
import com.hengmall.goods.model.api.FlashSaleReq;
import com.hengmall.goods.model.api.FlashSaleResp;
import com.hengmall.goods.model.api.GenerateSuppliersReq;
import com.hengmall.goods.model.api.GetVerificationCode;
import com.hengmall.goods.model.api.ImmediatePayReq;
import com.hengmall.goods.model.api.IndexResp;
import com.hengmall.goods.model.api.MyFootprintListResp;
import com.hengmall.goods.model.api.OrderDetailsReq;
import com.hengmall.goods.model.api.PagingReq;
import com.hengmall.goods.model.api.ProductAppraiseReq;
import com.hengmall.goods.model.api.ProductIdReq;
import com.hengmall.goods.model.api.ProductInfoReq;
import com.hengmall.goods.model.api.ProductInfoResp;
import com.hengmall.goods.model.api.ProductListReq;
import com.hengmall.goods.model.api.ProductListResp;
import com.hengmall.goods.model.api.ProductSearchResp;
import com.hengmall.goods.model.api.ProductStatusList;
import com.hengmall.goods.model.api.ReceiveCouponReq;
import com.hengmall.goods.model.api.ReceivingStateReq;
import com.hengmall.goods.model.api.Refund;
import com.hengmall.goods.model.api.RefundReq;
import com.hengmall.goods.model.api.SAppraiseResp;
import com.hengmall.goods.model.api.ShoppingListResp;
import com.hengmall.goods.model.api.ShopsLocationResp;
import com.hengmall.goods.model.api.SignInReq;
import com.hengmall.goods.model.api.UserInfo;
import com.hengmall.goods.model.api.WalletDetail;
import com.hengmall.goods.model.api.WxPay;
import com.hengmall.goods.model.constitute.AddressList;
import com.hengmall.goods.model.constitute.AddressOperation;
import com.hengmall.goods.model.constitute.Ads;
import com.hengmall.goods.model.constitute.Carousel;
import com.hengmall.goods.model.constitute.CollectionOperation;
import com.hengmall.goods.model.constitute.Coupon;
import com.hengmall.goods.model.constitute.FlashSale;
import com.hengmall.goods.model.constitute.FolderList;
import com.hengmall.goods.model.constitute.JsPayResult;
import com.hengmall.goods.model.constitute.OrderDetails;
import com.hengmall.goods.model.constitute.OrderDetailsList;
import com.hengmall.goods.model.constitute.OrderDetailsResp;
import com.hengmall.goods.model.constitute.PayParameter;
import com.hengmall.goods.model.constitute.ResourceCarousel;
import com.hengmall.goods.model.constitute.SCategory;
import com.hengmall.goods.model.constitute.TigLibrary;
import com.hengmall.goods.model.constitute.WechatRefundApiResult;
import com.hengmall.goods.service.KdniaoTrackQueryAPI;
import com.hengmall.goods.service.UserHandleService;
import com.hengmall.goods.service.WechatPayService;
import com.hengmall.goods.service.qcloud.CosService;
import com.hengmall.goods.service.qcloud.QcloudSmsService;
import com.hengmall.goods.util.CommonUtils;
import com.hengmall.goods.util.HttpReqUtil;
import com.hengmall.goods.util.JedisUtil;
import com.hengmall.goods.util.MD5Util;
import com.hengmall.goods.util.PublicHandleUtils;
import com.hengmall.goods.util.PublicUtil;
import com.hengmall.goods.util.WechatUtil;
import com.hengmall.goods.util.XmlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "商城小程序接口中心", description = "商城小程序接口中心")
@RestController
@RequestMapping("store/api/")
public class StoreController {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    RelCarouselDao relCarouselDao;
    @Autowired
    SResourcesDao sResourcesDao;
    @Autowired
    RelRecommendDao relRecommendDao;
    @Autowired
    RelIndexProductDao relIndexProductDao;
    @Autowired
    SCategoryDao sCategoryDao;
    @Autowired
    TigLibraryDao tigLibraryDao;
    @Autowired
    SProductDao sProductDao;
    @Autowired
    SAppraiseDao sAppraiseDao;
    @Autowired
    PAddressDao pAddressDao;
    @Autowired
    UsersDao usersDao;
    @Autowired
    SShoppingcartDao sShoppingcartDao;
    @Autowired
    SOrderDao sOrderDao;
    @Autowired
    RelCollectionDao relCollectionDao;
    @Autowired
    RelFootprintDao relFootprintDao;
    @Autowired
    SCouponDao sCouponDao;
    @Autowired
    RelCouponDao relCouponDao;
    @Autowired
    SOrderExtraDao sOrderExtraDao;
    @Autowired
    AdsDao adsDao;
    @Autowired
    FlashSaleDao flashSaleDao;
    @Autowired
    BrandsDao brandsDao;
    @Autowired
    WalletDetailsDao walletDetailsDao;
    @Autowired
    MsgCenterDao msgCenterDao;
    @Autowired
    FlashSaleTimelinessDao flashSaleTimelinessDao;
    @Autowired
    SuccessKilledDao successKilledDao;
    @Autowired
    RelProductDetailDao relProductDetailDao;
    @Autowired
    ActivityModuleDao activityModuleDao;
    @Autowired
    TbUserDao tbUserDao;
    @Autowired
    ApplyRefundDao applyRefundDao;
    @Autowired
    CombineOrderDao combineOrderDao;
    @Autowired
    ShopsDao shopsDao;
    @Autowired
    PlatformPlateDao platformPlateDao;
    @Autowired
    ShopsRightDao shopsRightDao;
    @Autowired
    ShopsCommendDao shopsCommendDao;
    @Autowired
    UserHandleService userHandleService;
    @Autowired
    WechatPayService wechatPayService;
    @Autowired
    CosService cosService;
    @Autowired
    QcloudSmsService qcloudSmsService;
    @Autowired
    KdniaoTrackQueryAPI kdniaoTrackQueryAPI;
    @Autowired 
    PlatformTagDao platformTagDao;
    @Autowired
    GroupProductDao groupProductDao;
    @Autowired
    TbTopicDao tbTopicDao;
    @Autowired
    CombineSaleDao combineSaleDao;
    @Autowired
    SystemConfigDao systemConfigDao;

    //腾讯云cos所需参数
    @Value("${cos.path}")
    private String cosPaths;

    //支付所需参数
    @Value("${small.appid}")
    private String appid;
    @Value("${small.mch_id}")
    private String mch_id;
    @Value("${small.apiKey}")
    private String apiKey;
    @Value("${small.notifyUrl}")
    private String notifyUrl;
    @Value("${small.unifiedOrder}")
    private String unifiedOrder;

    @Value("${kdniao.expCode}")
    private String expCode;
    @Value("${kdniao.expNo}")
    private String expNo;

    @Value("${server.token}")
    private String serverToken;

    @ApiOperation(value = "首页展示", notes = "包括：图片轮播、首页商品、推荐商品列表，商品分类",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = IndexResp.class)
    @ResponseBody
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public Ajax index(@RequestBody ApiRequest apiRequest) {

        logger.info("客户端请求数据（store/api/index）：{}", apiRequest.toString());

        //图片轮播
        List<Carousel> carousels = relCarouselDao.queryByType(RelCarouselEntity.Index);

        //平台资质
        List<PlatformTagEntity> platformTagList = platformTagDao.queryPlatformTagList();
        
        //一级分类集合
        List<SCategory> firstSCategories = sCategoryDao.queryByLevel(SCategoryEntity.Index);

        //活动模块
        List<Ads> adsList= adsDao.queryByCategoryId(SCategoryEntity.Index);
        
        //首页上新商品
        List<SProductEntity> productList = sProductDao.shopProductList();
        List<ProductListResp> productInfoList = new ArrayList<>();
        ProductListResp productListResp = new ProductListResp();
        for(SProductEntity productInfo : productList){
        	productListResp = new ProductListResp();
        	productListResp.setHeadimg(productInfo.getHeadimg());
        	productListResp.setId(productInfo.getId());
        	productListResp.setName(productInfo.getName());
        	productListResp.setPrice(productInfo.getPrice()*1.0/100);
        	productInfoList.add(productListResp);
        }

        //参数封装
        IndexResp indexResp = new IndexResp();
        indexResp.setCarousels(carousels);
        indexResp.setActivityModules(adsList);
        indexResp.setPlatformTagList(platformTagList);
        indexResp.setProductList(productInfoList);
        indexResp.setSCategories(firstSCategories);

        logger.info("服务端返回数据（store/api/index）：{}", indexResp.toString());
        return new Ajax(indexResp);
    }

	// TODO  后期有对搜索功能进行优化要求 
    @ApiOperation(value = "商品搜索", notes = "商品搜索",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductListResp.class)
    @ResponseBody
    @RequestMapping(value = "productSearch", method = RequestMethod.POST)
    public Ajax productSearch(@RequestBody ProductSearchResp productSearch) throws Exception {

        logger.info("客户端请求数据（store/api/productSearch）：{}", productSearch.toString());

        List<ProductListResp> byKeys = sProductDao.findByKeys(productSearch.getKeys());

        logger.info("服务端返回数据（store/api/productSearch）：{}", byKeys.toArray());
        return new Ajax(byKeys);
    }

    //TODO 二级分类详情页，新需求需要修改的页面
    @ApiOperation(value = "新需求的二级分类详情页", notes = "二级详情页",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "newCategory", method = RequestMethod.POST)
    public Ajax newCategory(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/category）：{}", apiRequest.toString());
        //参数封装
        JSONObject jsonObject = new JSONObject();
        
        //首先获取在对应的一级分类下的轮播图
        int typeId = apiRequest.getTypeIdOne();
        List<Carousel> carouselList = relCarouselDao.queryByType(typeId);
        
        //其次获取二级分类名称 根据一级查询子级
        List<SCategory> sCategories = sCategoryDao.queryByPId(typeId);
        
        // 然后拿到平台对应的分类下的板块相关数据，此接口已经写好
		List<PlatformPlateEntity> list = platformPlateDao.queryPlatformPlateList(typeId);
		for(PlatformPlateEntity platformPlate : list){
			int shopsId = platformPlate.getShops_id();
			String platform_ids = platformPlate.getPlatform_ids();
			platform_ids = platform_ids.substring(1, platform_ids.length()-1);
			List<ProductReq> list2 = platformPlateDao.queryPlatformPlateProductListBYshops(shopsId,platform_ids);
			platformPlate.setPlateProductList(list2);
		}
		jsonObject.put("carouselList", carouselList);
		jsonObject.put("sCategories", sCategories);
		jsonObject.put("sCategories", sCategories);
		jsonObject.put("PlateList", list);
		

        logger.info("服务端返回数据（store/api/category）：{}", jsonObject);
        return new Ajax(jsonObject);
    }

    
    @ApiOperation(value = "商品标签分类", notes = "商品标签分类，一级、二级列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = SCategory.class)
    @ResponseBody
    @RequestMapping(value = "category", method = RequestMethod.POST)
    public Ajax category(@RequestBody ApiRequest apiRequest) throws Exception {
        logger.info("客户端请求数据（store/api/category）：{}", apiRequest.toString());
        
      //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        List list = new ArrayList();

        //查询一级
        List<TigLibrary> firstTigLibrary = tigLibraryDao.queryByLevel(SCategoryEntity.Index);
        for (TigLibrary tigLibrary : firstTigLibrary) {
            int id = tigLibrary.getId();

            //根据一级查询子级
            List<TigLibrary> tigLibrarys = tigLibraryDao.queryByPId(id);
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", tigLibrary.getName());
            jsonObject.put("icon", tigLibrary.getIcon());
            jsonObject.put("children", tigLibrarys.toArray());
            list.add(jsonObject);
        }

        logger.info("服务端返回数据（store/api/productCtegory）：{}", list.toArray());
        return new Ajax(list);
    }


    @ApiOperation(value = "二级分类列表页面", notes = "二级分类列表页面",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "categoryOrder", method = RequestMethod.POST)
    public Ajax categoryOrder(@RequestBody CategoryOrderReq categoryOrder) throws Exception {

        logger.info("客户端请求数据（store/api/categoryOrder）：{}", categoryOrder.toString());

        int id = categoryOrder.getId();
        if (id <= 0) throw new Exception("商品类别主键ID不正确");
        
        int orderStatus = categoryOrder.getOrderStatus();

        //排序标识；默认1：综合，2：新品，3：销量，4：价格
        String order_desc = "name";
        if (orderStatus == 2) {
            order_desc = "id";
        } else if (orderStatus == 3) {
            order_desc = "salesvolume";
        } else if (orderStatus == 4) {
            order_desc = "price";
        }

        //根据商品类别主键ID查询商品信息
        List<ProductListResp> sProductEntities = sProductDao.queryBySCategoryId2(id,order_desc);

        logger.info("服务端返回数据（store/api/categoryOrder）：{}", sProductEntities);
        return new Ajax(sProductEntities);
    }

    //二级分类详情页面的商品列表需要进行重写，目前暂时不清楚规则，没写
    @ApiOperation(value = "商品列表", notes = "商品列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductListResp.class)
    @ResponseBody
    @RequestMapping(value = "getProductList", method = RequestMethod.POST)
    public Ajax getProductList(@RequestBody ProductListReq productListReq) throws Exception {

        logger.info("客户端请求数据（store/api/getProductList）：{}", productListReq.toString());

        int id = productListReq.getId();
        int orderStatus = productListReq.getOrderStatus();
        if (id <= 0 || orderStatus <= 0) throw new Exception("请求数据不正确，请重新请求！");

        //排序标识；默认1：综合，2：新品，3：销量，4：价格
        String order_desc = "name";
        if (orderStatus == 2) {
            order_desc = "id";
        } else if (orderStatus == 3) {
            order_desc = "salesvolume";
        } else if (orderStatus == 4) {
            order_desc = "price";
        }

        //根据商品类别主键ID查询商品信息
        List<ProductListResp> sProductEntities = sProductDao.queryByCategoryId(id, order_desc);

        logger.info("服务端返回数据（store/api/getProductList）：{}", sProductEntities.toArray());
        return new Ajax(sProductEntities);
    }

    @ApiOperation(value = "商品介绍", notes = "商品介绍",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductInfoResp.class)
    @ResponseBody
    @RequestMapping(value = "productInfo", method = RequestMethod.POST)
    public Ajax productInfo(@RequestBody ProductInfoReq productInfoReq) throws Exception {

        logger.info("客户端请求数据（store/api/productInfo）：{}", productInfoReq.toString());

        //token认证
        UsersEntity userInfo = auth(productInfoReq.getToken());
        int user_id = userInfo.getId();
        
        //商品ID
        int id = productInfoReq.getId();
        
        // 美元兑换人民币的汇率,由后台的系统配置进行管理
        int rate = systemConfigDao.queryRateBycode();

        //商品SKU
        String attribute = "";

        //限时抢购的实际支付价格
//      double actualPrice = 0;  rushPrice": 400, "spellPrice

        // 0：未抢购，1：已抢购
        int flag = 0;

        //业务状态码：0：普通商品信息，1：限时抢购商品信息，2：拼单
        int status = productInfoReq.getStatus();

        //根据请求的商品ID查询数据
        SProductEntity sProductEntity = sProductDao.queryById(id);
        if (PublicUtil.isEmpty(sProductEntity)) throw new Exception("无此商品");
        int product_id = sProductEntity.getId();
        int shops_id = sProductEntity.getShops_id();
        int price_type = sProductEntity.getPrice_type();
        ShopsInfo shopsInfo = shopsDao.queryById(shops_id);
        String state_name = sProductDao.queryStateNameById(sProductEntity.getState_id()); 
        String tagids = sProductEntity.getTagids();
        
        int shops_product_id = sProductEntity.getShops_product_id();
        //根据商品ID查询是否有收藏
        int collection_status = relCollectionDao.queryIsCollection(user_id, id);


        //根据商品ID查询关联图片，前端轮播
        List<ResourceCarousel> resourceCarousels = sResourcesDao.queryByproductId(product_id, RelProductDetailEntity.Commodity_Type);
        
        String rights = sProductEntity.getRights();
        rights = rights.substring(1, rights.length()-1);
        
        //商品资质数据
        List<ShopsRight> shopsRigthList = shopsRightDao.queryByShopsId2(rights);
        
        //商品组合推荐,目前的组合可能还需要修改
        List<ShopsCommend> shopsCommendList = shopsCommendDao.queryByShopsId(shops_id,id);
        for(ShopsCommend shopsCommend : shopsCommendList ){
        	String productStr = shopsCommend.getProduct_ids();
        	String[] str = productStr.substring(1, productStr.length()-1).split(",");
        	List<ProductListResp> sProductList = new ArrayList<>();
        	for(String productId : str){
        		ProductListResp sProduct = sProductDao.queryByIdResp(Integer.valueOf(productId.trim()));
        		sProduct.setPrice(sProduct.getPrice()*1.0/100);
        		sProductList.add(sProduct);
        	}
        	shopsCommend.setProductList(sProductList);
        	shopsCommend.setGroup_price(shopsCommend.getGroup_price()*1.0/100);
        }
        
        // 根据个人浏览记录生成猜你喜欢
        List<MyFootprintListResp> sProductEntities = sProductDao.queryGuessByUserId(user_id);
        
        
        //获取商品详情图片或视频资源
        List<ResourceCarousel> detailResource = sResourcesDao.queryByproductId(product_id, RelProductDetailEntity.Details_Type);
        
        //获取商品评价
        List<SAppraiseResp> sAppraiseResp = new ArrayList<>();
        List<SAppraiseEntity> sAppraiseEntities = sAppraiseDao.queryByproductId(id);
        if (sAppraiseEntities.size() != 0) {
            for (SAppraiseEntity sAppraiseEntity : sAppraiseEntities) {

                JSONObject jsonObject = new JSONObject();
                if (sAppraiseEntity.getResources() != null && !sAppraiseEntity.getResources().equals("")) {
                    jsonObject = JSONObject.parseObject(sAppraiseEntity.getResources());
                }

                String nickname = sAppraiseEntity.getNickname();
                StringBuffer nicknameStr = new StringBuffer();
                nicknameStr.append(nickname.substring(0, 1)).append("***").append(nickname.substring(nickname.length() -1));
                JSONObject jso = JSONObject.parseObject(sAppraiseEntity.getAttr());
//                JSONObject jso =JSON.parseObject(new String(sAppraiseEntity.getAttr().getBytes("ISO8859-1"),"UTF-8"));
                //参数封装
                SAppraiseResp sAppraise = new SAppraiseResp();
                sAppraise.setContext(sAppraiseEntity.getContext());
                sAppraise.setLike(sAppraiseEntity.getLike());
                sAppraise.setProductId(sAppraiseEntity.getProductid());
                sAppraise.setName(nicknameStr.toString());
                sAppraise.setDate(sAppraiseEntity.getCtime().substring(0, 10));
                sAppraise.setResources(jsonObject);
                sAppraise.setUrl(sAppraiseEntity.getUrl());
                sAppraise.setAttr(jso);
                
                sAppraiseResp.add(sAppraise);
            }
        }
        
        //SKU常量
        final String[] SKU_TYPE = {"one", "two", "three", "four", "five"};
        
        if (status == 1) {
            int flash_sale_id = productInfoReq.getFlashSaleId();
            if (flash_sale_id <= 0) throw new Exception("限时抢购ID不正确");

            //根据flash_sale_id 和 user_id 查询 该用户是否已经抢购过该商品
            SuccessKilledEntity isKilled = successKilledDao.findByFlashSaleId(user_id, flash_sale_id);
            if (PublicUtil.isNotEmpty(isKilled)) flag = 1;
        }

        //（普通购买）
        attribute = sProductEntity.getAttribute();

        //json处理 SKU 处理
        JSONObject attr = JSONObject.parseObject(attribute);

        JSONArray standard = attr.getJSONArray("standard");
        JSONArray details = attr.getJSONArray("details");
        if (PublicUtil.isNotEmpty(standard) && PublicUtil.isNotEmpty(details)) {
            int i = 0;
            JSONObject jsonObject = new JSONObject();
            for (Object stand : standard) {
                JSONObject json = JSONObject.parseObject(stand + "");
                String name = json.getString("name");
                jsonObject.put(SKU_TYPE[i++], name);
            }
            logger.info("转换后的json：{}", jsonObject);

            int j = i;

            //处理detail后数据的重新封装
            List detailList = new ArrayList();

            for (Object detail : details) {
                JSONObject json = JSONObject.parseObject(detail + "");

                JSONObject jsonObjOne = new JSONObject(16, true);
                for (int s = 0; s < j; s++) {
                    String value = json.getString(SKU_TYPE[s]);
                    String key = jsonObject.getString(SKU_TYPE[s]);
                    jsonObjOne.put(key, value);
                }
                
              //业务状态码：0：普通商品信息，1：限时抢购商品信息，2：拼单
                JSONObject jsonObjTwo = new JSONObject(16, true);
                if(status == 0){
                    if(price_type == 2){
    	                jsonObjTwo.put("price", json.getDouble("price")*rate);
    	                jsonObjTwo.put("index", json.getIntValue("index"));
    	                jsonObjTwo.put("stock", json.getIntValue("stock"));
    	                jsonObjTwo.put("newprice", json.getDouble("price")); //多价格美元的处理
                    }else{
                        jsonObjTwo.put("price", json.getDouble("price"));
                        jsonObjTwo.put("index", json.getIntValue("index"));
                        jsonObjTwo.put("stock", json.getIntValue("stock"));
                    }
                	
                }else if(status == 1){
                    jsonObjTwo.put("price", json.getDouble("rushPrice"));
                    jsonObjTwo.put("index", json.getIntValue("index"));
                    jsonObjTwo.put("stock", json.getIntValue("stock"));
                }else if(status == 2){
                    jsonObjTwo.put("price", json.getDouble("spellPrice"));
                    jsonObjTwo.put("index", json.getIntValue("index"));
                    jsonObjTwo.put("stock", json.getIntValue("stock"));
                }
                

                //两个json合并
                JSONObject jsonObj = CommonUtils.deepMerge(jsonObjOne, jsonObjTwo);
                logger.info("两个json合并后的结果：{}", jsonObj);

                detailList.add(jsonObj);
            }

            attr.put("standard", standard);
            attr.put("details", detailList);
            logger.info("处理过后的最终attr：{}", attr);
        }
        //SKU封装
        sProductEntity.setAttr(attr);

        //JSONArray处理
        String description = sProductEntity.getDescription();
        JSONArray jsonArray = JSONObject.parseArray(description);

        //参数封装
        ProductInfoResp productInfoResp = new ProductInfoResp();
        productInfoResp.setIsAllowrefund(sProductEntity.getAllowrefund());
        productInfoResp.setAttr(attr);
        productInfoResp.setResourceCarousels(resourceCarousels);
        productInfoResp.setName(sProductEntity.getName());
        if(status == 0){
            if(price_type == 2){
    	        productInfoResp.setPrice(sProductEntity.getPrice() * 1.0 / 100*rate);
    	        productInfoResp.setNewprice(sProductEntity.getPrice() * 1.0 / 100);
            }else if(price_type == 1) {
            	productInfoResp.setPrice(sProductEntity.getPrice() * 1.0 / 100);
            }
        }else{
        	productInfoResp.setPrice(sProductEntity.getPrice() * 1.0 / 100);
        }
        productInfoResp.setStock(sProductEntity.getStock());
        productInfoResp.setFreight(sProductEntity.getFreight() * 1.0 / 100);
        productInfoResp.setOldprice(sProductEntity.getOldprice() * 1.0 / 100);
        productInfoResp.setDesc(jsonArray);
        productInfoResp.setCollectionStatus(collection_status);
        productInfoResp.setActualPrice(0);
        productInfoResp.setCombineNum(sProductEntity.getCombine_num());
        productInfoResp.setHeadImg(sProductEntity.getHeadimg());
        productInfoResp.setShopsId(sProductEntity.getShops_id());
        productInfoResp.setShopsImg(shopsInfo.getUrl());
        productInfoResp.setShopsName(shopsInfo.getShopsName());
        productInfoResp.setShopsCommendList(shopsCommendList);
        productInfoResp.setShopsRigthList(shopsRigthList);
        productInfoResp.setSProductEntities(sProductEntities);
        productInfoResp.setDetailResource(detailResource);
        productInfoResp.setState_icon(sProductEntity.getState_icon());
        productInfoResp.setSales(sProductEntity.getSalesvolume());
        productInfoResp.setSAppraiseResp(sAppraiseResp);
        productInfoResp.setState_name(state_name);
        productInfoResp.setEndimg(sProductEntity.getEndimg());
        productInfoResp.setUser_id(user_id);
        productInfoResp.setShops_product_id(shops_product_id);
        
        logger.info("服务端返回数据（store/api/productInfo）：{}", productInfoResp.toString());
        return new Ajax(productInfoResp);
    }

    @ApiOperation(value = "商品所有评价", notes = "该商品的所有评价",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = SAppraiseResp.class)
    @ResponseBody
    @RequestMapping(value = "productAppraise", method = RequestMethod.POST)
    public Ajax productAppraise(@RequestBody ProductAppraiseReq productAppraiseReq) throws Exception {

        logger.info("客户端请求数据（store/api/productAppraise）：{}", productAppraiseReq.toString());

        int id = productAppraiseReq.getId();
        
        List<SAppraiseResp> sAppraiseResp = new ArrayList<>();

        List<SAppraiseEntity> sAppraiseEntities = sAppraiseDao.queryByproductId(id);
        if (sAppraiseEntities.size() != 0) {
            for (SAppraiseEntity sAppraiseEntity : sAppraiseEntities) {

                JSONObject jsonObject = new JSONObject();
                if (sAppraiseEntity.getResources() != null && !sAppraiseEntity.getResources().equals("")) {
                    jsonObject = JSONObject.parseObject(sAppraiseEntity.getResources());
                }

                String nickname = sAppraiseEntity.getNickname();
                StringBuffer nicknameStr = new StringBuffer();
                nicknameStr.append(nickname.substring(0, 1)).append("***").append(nickname.substring(nickname.length() -1));
                //参数封装
                SAppraiseResp sAppraise = new SAppraiseResp();
                sAppraise.setContext(sAppraiseEntity.getContext());
                sAppraise.setLike(sAppraiseEntity.getLike());
                sAppraise.setProductId(sAppraiseEntity.getProductid());
                sAppraise.setName(nicknameStr.toString());
                sAppraise.setDate(sAppraiseEntity.getCtime().substring(0, 10));
                sAppraise.setResources(jsonObject);
                sAppraiseResp.add(sAppraise);
            }
        }

        logger.info("服务端返回数据（store/api/productAppraise）：{}", sAppraiseResp.toArray());
        return new Ajax(sAppraiseResp);
    }
    
    @ApiOperation(value = "我的所有评价", notes = "我的所有评价",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = SAppraiseResp.class)
    @ResponseBody
    @RequestMapping(value = "myAppraise", method = RequestMethod.POST)
    public Ajax myAppraise(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/apiRequest）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();
        
        List<SAppraiseResp> sAppraiseResp = new ArrayList<>();

        List<SAppraiseEntity> sAppraiseEntities = sAppraiseDao.queryByUserId(user_id);
        if (sAppraiseEntities.size() != 0) {
            for (SAppraiseEntity sAppraiseEntity : sAppraiseEntities) {

                JSONObject jsonObject = new JSONObject();
                if (sAppraiseEntity.getResources() != null && !sAppraiseEntity.getResources().equals("")) {
                    jsonObject = JSONObject.parseObject(sAppraiseEntity.getResources());
                }
                int product_id = sAppraiseEntity.getProductid();
                ProductListResp productResp =  sProductDao.queryByIdResp(product_id);
                String nickname = sAppraiseEntity.getNickname();
                StringBuffer nicknameStr = new StringBuffer();
                nicknameStr.append(nickname.substring(0, 1)).append("***").append(nickname.substring(nickname.length() -1));
                
                //参数封装
                SAppraiseResp sAppraise = new SAppraiseResp();
                sAppraise.setContext(sAppraiseEntity.getContext());
                sAppraise.setLike(sAppraiseEntity.getLike());
                sAppraise.setProductId(sAppraiseEntity.getProductid());
                sAppraise.setName(nicknameStr.toString());
                sAppraise.setDate(sAppraiseEntity.getCtime().substring(0, 10));
                sAppraise.setResources(jsonObject);
                sAppraise.setProduct_name(productResp.getName());
                sAppraise.setProduct_price(productResp.getPrice());
                sAppraise.setProduct_url(productResp.getHeadimg());
                sAppraise.setUrl(sAppraiseEntity.getUrl());
                JSONObject jso = JSONObject.parseObject(sAppraiseEntity.getAttr());
                sAppraise.setAttr(jso);
                sAppraiseResp.add(sAppraise);
            }
        }

        logger.info("服务端返回数据（store/api/productAppraise）：{}", sAppraiseResp.toArray());
        return new Ajax(sAppraiseResp);
    }


    /**
     * TODO 文件上传
     *
     * @param id     商品ID
     * @param file   上传的文件
     * @param status 用于区分文件上传到哪个cosPath
     */
    @ApiOperation(value = "文件上传", notes = "文件上传",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public Ajax uploadFile(@RequestParam(value = "id") String id,
                           @RequestParam(value = "file") CommonsMultipartFile file,
                           @RequestParam(value = "status", defaultValue = "0") int status) throws Exception {

        logger.info("客户端请求数据（store/api/uploadFile）：上传商品的对应ID：{}；" +
                "上传文件的状态码：{}；上传的资源文件：{}", id, status, file.getOriginalFilename());


        //从配置文件中获取cosPath参数
        String cosPath = PublicHandleUtils.getCosPath(cosPaths, status);

        String access_url = "";

        //文件上传的类型 默认 1: 图片
        int type = 1;

        //获取客户端需要上传的文件
        CommonsMultipartFile cmf = file;

        if (cmf != null) {

            //获取文件的后缀名
            String suffixName = file.getOriginalFilename();
            int indexOf = suffixName.lastIndexOf(".");
            if (indexOf < 0) indexOf = suffixName.length();
            suffixName = suffixName.substring(indexOf);         //需要测试
            logger.info("上传的文件后缀名为：{}", suffixName);

            //判断上传的文件是图片还是视频
            type = PublicHandleUtils.getType(suffixName);

            //生成文件名
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            //避免快速点击上传造成同名，提示文件已存在错误：生成4位随机数
            fileName = fileName + CommonUtils.getRandomStringByLength(4, 0);

            //加载对象存储 COS
            cosService.load();
            InputStream is = null;
            try {
                is = cmf.getInputStream();

                //文件上传到腾讯云
                String isUpload = cosService.uploadByIo(cosPath + fileName + suffixName, is);

                JSONObject jsonObject = JSONObject.parseObject(isUpload);
                int code = jsonObject.getIntValue("code");

                if (code >= 0) {
                    JSONObject data = jsonObject.getJSONObject("data");
                    access_url = data.getString("access_url");
                    logger.info("商品ID（{}）上传的资源文件地址为：{}", id, access_url);
                } else {
                    logger.warn("上传出错，错误信息：{code:{},message:{}}",
                            jsonObject.getIntValue("code"), jsonObject.getString("message"));

                    return new Ajax(jsonObject.getIntValue("code"), jsonObject.getString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cosService.getCosClient().shutdown();
            }
        } else {
            throw new Exception("无法获取上传的文件！");
        }


        SResourcesEntity resourcesEntity = new SResourcesEntity();
        sResourcesDao.saveResources(access_url, type, resourcesEntity);

        //新增上传文件id
        int resourcesId = resourcesEntity.getId();


        //参数封装
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessUrl", access_url);
        jsonObject.put("resourcesId", resourcesId);
        logger.info("服务端返回数据（store/api/uploadFile）：{}", jsonObject.toJSONString());
        return new Ajax(jsonObject);
    }


    //TODO 删除上传文件
    @ApiOperation(value = "删除上传文件", notes = "删除上传文件",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delFile", method = RequestMethod.POST)
    public Ajax delFile(@RequestBody DelFile delFile) throws Exception {

        logger.info("客户端请求数据（store/api/delFile）：{}", delFile.toString());

        String fileName = delFile.getFileName();

        //加载对象存储 COS
        cosService.load();

        String delInfo = cosService.delete("/img/" + fileName);

        cosService.getCosClient().shutdown();

        JSONObject jsonObject = JSONObject.parseObject(delInfo);
        int code = jsonObject.getIntValue("code");

        if (code < 0) {
            logger.warn("文件名（" + fileName + "）删除出错，错误信息：{code:" + jsonObject.getIntValue("code")
                    + ",message:" + jsonObject.getString("message") + "}");

            return new Ajax(jsonObject.getIntValue("code"), jsonObject.getString("message"));
        } else {
            logger.info("文件名（" + fileName + "）删除后的信息：{}", delInfo);
        }

        return new Ajax("删除成功");
    }


    @ApiOperation(value = "商品评价", notes = "收货后对该商品的评价",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AppraiseListReq.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "appraise", method = RequestMethod.POST)
    public Ajax appraise(@RequestBody AppraiseListReq appraiseList) throws Exception {

        logger.info("客户端请求数据（store/api/appraise）：{}", appraiseList.toString());

        List<AppraiseReq> list = appraiseList.getAppraiseList();
        //token认证
        UsersEntity userInfo = auth(appraiseList.getToken());
        int user_id = userInfo.getId();
        
        for(AppraiseReq appraise : list ){
	        int id = appraise.getId();  //需要评价的店铺商品ID
	        int orderId = appraise.getOrderId(); //需要评价的订单ID
	        
	        int deliverySpeed = appraise.getDeliverySpeed();    //发货速度
	        int matching = appraise.getMatching();          //描述相符
	        int score = appraise.getScore();            //商品星级评分
	        String context = appraise.getContext();            //商品星级评分
	        int servicequality = appraise.getServiceQuality();//商品星级评分
	        int serviceattitude = appraise.getServiceAttitude();
	        String url = userInfo.getAvatar_url();
	        
	        SOrderExtraEntity sOrderExtraEntity = sOrderExtraDao.queryById(orderId,id);
	        if(PublicUtil.isEmpty(sOrderExtraEntity)){
	        	throw new Exception("无效的订单或者商品ID");
	        }
	        String attr = sOrderExtraEntity.getAttrs();
	        
	        //商品评价的图片
	        List<String> accessUrls = appraise.getAccessUrls();
	        if (accessUrls != null && accessUrls.size() != 0) {
	            JSONObject resources = new JSONObject();
	            resources.put("img", accessUrls);
	            sAppraiseDao.insert(id, user_id, context, matching, deliverySpeed, score,
	                    servicequality, serviceattitude, resources.toJSONString(),url,attr);
	        } else {
	            sAppraiseDao.insertExceptResource(id, user_id, context, matching, deliverySpeed, score,
	                    servicequality, serviceattitude,url,attr);
	        }

	        //修改评价状态码
	        sOrderExtraDao.updateAppraiseStatus(2, appraise.getOrderId(), id);
        }
        return new Ajax("评价成功");
    }


    @ApiOperation(value = "新增、编辑收货地址", notes = "新增、编辑收货地址",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "addressOperation", method = RequestMethod.POST)
    public Ajax addressOperation(@RequestBody AddressOperation address) throws Exception {
        logger.info("客户端请求数据（store/api/addressOperation）：{}", address.toString());

        //token认证
        UsersEntity userInfo = auth(address.getToken());
        int user_id = userInfo.getId();

        int status = address.getStatus();

        String desc = "新增成功";

        if (status == 0) {
            pAddressDao.insert(user_id, address.getUname(), address.getPhone(), address.getZip_code(),
                    address.getProvince(), address.getCity(), address.getArea(),
                    "", address.getAddress());
        } else {
            PAddressEntity pAddressEntity = pAddressDao.queryById(address.getId());
            if (pAddressEntity == null) throw new Exception("无效的收货地址ID");

            pAddressDao.updateById(address);

            desc = "编辑成功";
        }

        return new Ajax(desc);
    }

    @Deprecated
    @ApiOperation(value = "收货地址", notes = "编辑收货地址",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "editAddress", method = RequestMethod.POST)
    public Ajax editAddress(@RequestBody EditAddressReq editAddressReq) throws Exception {

        logger.info("客户端请求数据（store/api/editAddress）：{}", editAddressReq.toString());
        String token = editAddressReq.getToken();

        PAddressEntity pAddressEntity = pAddressDao.queryById(editAddressReq.getId());
        if (pAddressEntity == null) throw new Exception("无效的收货地址ID");

//        pAddressDao.updateById(editAddressReq);

        return new Ajax("修改成功");
    }


    @ApiOperation(value = "收货地址列表", notes = "收货地址列表，首地址为默认收货地址",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = AddressList.class)
    @ResponseBody
    @RequestMapping(value = "addressList", method = RequestMethod.POST)
    public Ajax addressList(@RequestBody PagingReq apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/addressList）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();

        int page = apiRequest.getPage();
        int limit = apiRequest.getLimit();
        if (page <= 0) page = 0;
        if (limit <= 0) limit = 20;
        int offset = page * limit;
        logger.info("limit值：{}、page值：{}", limit, offset);

        //根据用户id查询收货地址列表
        List<AddressList> addressLists = pAddressDao.getAddressList(user_id, offset, limit);

        return new Ajax(addressLists);
    }

    @ApiOperation(value = "设置默认收货地址", notes = "设置默认收货地址",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "setDefaultAddress", method = RequestMethod.POST)
    public Ajax addr(@RequestBody DefaultAddressReq defaultAddressReq) throws Exception {

        logger.info("客户端请求数据（store/api/setDefaultAddress）：{}", defaultAddressReq.toString());

        //token认证
        UsersEntity userInfo = auth(defaultAddressReq.getToken());
        int user_id = userInfo.getId();

        int address_id = defaultAddressReq.getId();

        usersDao.updateAddressId(user_id, address_id);

        return new Ajax("设置成功");
    }


    @ApiOperation(value = "删除收货地址", notes = "删除收货地址",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delAddress", method = RequestMethod.POST)
    public Ajax delAddress(@RequestBody DelAddressReq delAddressReq) throws Exception {

        logger.info("客户端请求数据（store/api/delAddress）：{}", delAddressReq.toString());

        int id = delAddressReq.getId();
        pAddressDao.delById(id);

        return new Ajax("删除成功");
    }

    @ApiOperation(value = "加入购物车", notes = "加入购物车",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "addCart", method = RequestMethod.POST)
    public Ajax addCart(@RequestBody AddCartReq addCartReq) throws Exception {

        logger.info("客户端请求数据（store/api/addCart）：{}", addCartReq.toString());

        // 美元兑换人民币的汇率,由后台的系统配置进行管理
        int rate = systemConfigDao.queryRateBycode();
        
        //token认证
        UsersEntity userInfo = auth(addCartReq.getToken());
        int user_id = userInfo.getId();

        //商品ID
        int product_id = addCartReq.getProductId();
        
        //店铺ID
        int shops_id = addCartReq.getShopsId();

        //SKU索引ID
        int sku_index = addCartReq.getSkuIndex();
        
        //数量
        int num = addCartReq.getSum();
        
        int price_type = addCartReq.getPriceType();

        int price = 0;
        //根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
        JSONObject jsonObject = userHandleService.checkSKUBySKUIndex(sku_index, product_id);
        if (PublicUtil.isEmpty(jsonObject)) throw new Exception("无此SKU值");
        if(price_type == 1){ //价格类型为1时为人民币
        //根据SKU的价格支付,,注意此处的价格改为商品SKU单价
        	price = (int) (jsonObject.getDouble("price") * 100);
        }else if(price_type == 2) {//价格类型为2时为美元兑换后的人民币
        	price = (int) (jsonObject.getDouble("price") * 100) * rate;
        }

        logger.info("加入购物车后的sku值：{}", jsonObject);

        SShoppingcartEntity shoppingcart=sShoppingcartDao.queryProductByUserId(user_id, shops_id, product_id,sku_index);
        if(PublicUtil.isEmpty(shoppingcart)){
        	sShoppingcartDao.insert(product_id, jsonObject.toJSONString(),num, user_id,shops_id,sku_index,price);
        }else{
        	shoppingcart.setSum(shoppingcart.getSum()+1);
        	sShoppingcartDao.updateById(shoppingcart);
        }

        return new Ajax("添加成功");
    }


    @ApiOperation(value = "购物车列表", notes = "购物车列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ShoppingListResp.class)
    @ResponseBody
    @RequestMapping(value = "getShoppingList", method = RequestMethod.POST)
    public Ajax ShoppingCartList(@RequestBody ApiRequest apiRequest) throws Exception {
    	// TODO 需要进行店铺信息改造
        logger.info("客户端请求数据（store/api/getShoppingList）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();
        
        List<SShoppingcartEntity> shopsInfoLists = sShoppingcartDao.selectShopsByUserId(user_id);
        List<ShopsLocationResp> shopslist = new ArrayList<>();
        if(PublicUtil.isNotEmpty(shopsInfoLists)){
        	
        	for(SShoppingcartEntity shopsInfo : shopsInfoLists){
        	int shops_id = shopsInfo.getShops_id();
        	List<ShoppingListResp> list = new ArrayList<>();
	        List<SShoppingcartEntity> shoppingLists = sShoppingcartDao.selectByUserId(user_id,shops_id);
	        
	        ShopsLocation shopsLocation = shopsDao.queryShopsByUserId(shops_id);
	        if(!PublicUtil.objIsEmpty(shopsLocation)){
		        ShopsLocationResp shopsLocationResp = new ShopsLocationResp();
		        shopsLocationResp.setAvatar_url(shopsLocation.getAvatar_url());
		        shopsLocationResp.setShops_id(shops_id);
		        shopsLocationResp.setShops_name(shopsLocation.getShops_name());
	        if (PublicUtil.isNotEmpty(shoppingLists)) {
	            for (SShoppingcartEntity shoppingList : shoppingLists) {
	
	                String attr = shoppingList.getAttr();
	
	                JSONObject att = new JSONObject();
	                if (PublicUtil.isNotEmpty(attr)) {
	                    att = JSONObject.parseObject(attr);
	                }
	
	                //参数封装
	                ShoppingListResp shoppingListResp = new ShoppingListResp();
	                shoppingListResp.setAttr(att);
	                shoppingListResp.setHeadimg(shoppingList.getHeadimg());
	                shoppingListResp.setId(shoppingList.getId());
                    shoppingListResp.setPrice(shoppingList.getPrice()*1.0/100);
	                shoppingListResp.setProductId(shoppingList.getProduct_id());
	                shoppingListResp.setSum(shoppingList.getSum());
	                shoppingListResp.setName(shoppingList.getName());
	                list.add(shoppingListResp);
	            }
        	}else{
        		throw new Exception("购物车商品的店铺参数错误！");
        	}
	        shopsLocationResp.setShoppingList(list);
	        shopslist.add(shopsLocationResp);
	        }
    	}
	}
        logger.info("服务端返回数据（store/api/getShoppingList）：{}",shopslist);
        return new Ajax(shopslist);
    }


    @ApiOperation(value = "删除购物车商品", notes = "删除购物车商品",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delShoppingCarts", method = RequestMethod.POST)
    public Ajax delShoppingCarts(@RequestBody DelShoppingCarts delShoppingCarts) throws Exception {

        logger.info("客户端请求数据（store/api/delShoppingCarts）：{}", delShoppingCarts.toString());
        String token = delShoppingCarts.getToken();

        List<Integer> ids = delShoppingCarts.getIds();
        if (ids.size() <= 0) throw new Exception("未选择需要删除的购物车商品！");

        String id = StringUtils.strip(ids.toString(), "[]");

        logger.info("需要删除购物车的商品id集合：{}", id);

        sShoppingcartDao.delById(id);

        return new Ajax("删除成功");
    }

    @ApiOperation(value = "待付款、商品收、发货状态及所有订单", notes = "待付款、商品 收、发货状态及所有订单",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = ProductStatusList.class)
    @ResponseBody
    @RequestMapping(value = "receivingState", method = RequestMethod.POST)
    public Ajax pendingDelivery(@RequestBody ReceivingStateReq receivingState) throws Exception {

        logger.info("客户端请求数据（store/api/receivingState）：{}", receivingState.toString());

        //token认证
        UsersEntity userInfo = auth(receivingState.getToken());
        int user_id = userInfo.getId();
        
        // 美元兑换人民币的汇率,由后台的系统配置进行管理
        int rate = systemConfigDao.queryRateBycode();

        //请求状态码：0:待付款，1:待发货，2:待收货，3:已收货 99:所有订单
        int flag = receivingState.getReceivingFlag();

        int page = receivingState.getPage();
        int limit = receivingState.getLimit();

        if (page <= 0) page = 1;
        if (limit <= 0) limit = 20;
        int offset = (page-1)*limit;
        
        logger.info("limit值：{}、offset值：{}", limit, offset);

        //根据user_id 和 支付状态 查询 收、发货状态 及所有订单状态
        List<SProductEntity> sProductEntities = new ArrayList<>();
        List<ShopsOrderListEntity> shopsOrderListEntity = new ArrayList<>();
        List<ShopsLocation> shopsLocationList = new ArrayList<>();
        shopsOrderListEntity = sProductDao.queryShopsByUserId(user_id, offset, limit);
        Double totalPrice = 0.00; 
        
        for(ShopsOrderListEntity shopsOrderList :shopsOrderListEntity){
        	int shopsId  = shopsOrderList.getShopsId();
        	String out_trade_no = shopsOrderList.getOut_trade_no();
        	ShopsLocation shopsInfo = new ShopsLocation();
        	int order_id = shopsOrderList.getOrder_id();
        	int appraise_status = shopsOrderList.getAppraise_status();
        	int total_price = shopsOrderList.getTotal_price();
        	List productLists = new ArrayList();
        	//业务支付状态码；默认1：单独购买（商品），2：发起拼单（商品），3：钱包充值 4：限时抢购 5:组合搭配购买
        	int buy_status = shopsOrderList.getBuy_status();
        	String pay_no = shopsOrderList.getPay_no();
        	shopsInfo =  shopsDao.queryShopsByUserId(shopsId);
        	totalPrice = 0.00; 
            //根据不同的状态码查询不同的订单
            if (flag == SOrderExtraEntity.ALL_ORDERS) {//99:所有订单
                sProductEntities = sProductDao.queryOrderByUserId(user_id, offset, limit,shopsId,out_trade_no);
            } else {
                //支付状态
                int order_status = SOrderEntity.ALREADY_STATUS;

                if (flag == SOrderExtraEntity.PENDING_PAYMENT_STATUS) { //0:待付款
                    order_status = SOrderEntity.PENDING_STATUS;
                    sProductEntities = sProductDao.queryDeliverByUserId(user_id, order_status, offset,limit,shopsId,out_trade_no);
                }else if (flag == SOrderExtraEntity.PENDING_DELIVERY_STATUS) {//1:待发货
                    order_status = SOrderEntity.PENDING_DELIVERY_STATUS;
                    sProductEntities = sProductDao.queryDeliverByUserId(user_id, order_status, offset,limit,shopsId,out_trade_no);
                }else if (flag == SOrderExtraEntity.UNCOLLECTED_GOODS_STATUS) {//2:待收货
                    order_status = SOrderEntity.UNCOLLECTED_GOODS_STATUS;
                    sProductEntities = sProductDao.queryDeliverByUserId(user_id, order_status, offset,limit,shopsId,out_trade_no);
                }else if (flag == SOrderExtraEntity.RECEIVED_STATUS) {//3:待评价
                    order_status = SOrderEntity.RECEIVED_STATUS;
                    sProductEntities = sProductDao.queryDeliverByUserId(user_id, order_status, offset,limit,shopsId,out_trade_no);
                }
            }
            logger.info("被查询的订单状态（flag）：{}，收、发货状态（flag）：{}",flag,sProductEntities);
            
        /**
         * 订单 按钮状态：
         * 待支付：取消订单
         * 待发货：退款
         * 待收货、已收货：退货
         */
            //已收货的商品可做逻辑删除
            int isDeletion = ProductStatusList.DEL_NO;
            
            //取消订单按钮状态码；0：不可取消，1：可取消
            int isCancelOrder = 1;
            
            //立即付款按钮状态码；0：无立即付款，1：立即付款
            int isPayment = 1;
            
            //提醒发货按钮状态码；0：无提醒发货，1：提醒发货
            int isReminding = 0;
            
            //查看物流按钮状态码；0：无查看物流，1：查看物流
            int isLogistics = 0;
            
            //确认收货按钮状态码；0：无确认收货，1：确认收货
            int isCollectGoods = 0;
            
            //退款按钮状态码；0：无退款，1：可退款，2：已申请退款，3：已退款，4：拒绝退款
            int isRefund = 0;
            
            int status = 0;

            
        //遍历对参数处理
        if (sProductEntities.size() != 0) {
        	//需要对循环进行拼凑增多店铺的信息，此处的循环里面每一个是一条订单数据
            for (SProductEntity sProductEntity : sProductEntities) {
                //支付状态
                int payment_status = sProductEntity.getPayment_status();

                int product_id = sProductEntity.getId();
                SOrderExtraEntity sOrderExtraEntity = sOrderExtraDao.queryById(order_id, product_id);
                String attr = sOrderExtraEntity.getAttrs();
                JSONObject jsonAttrs = JSONObject.parseObject(attr);
                int sku_index = sOrderExtraEntity.getSku_index();
                
              //根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
                JSONObject jsonObject = userHandleService.checkSKUBySKUIndex( sku_index, product_id);
                if (PublicUtil.isEmpty(jsonObject)) throw new Exception("无此SKU值");
                Double singlePrice = 0.00;
                if(buy_status == 1){
                    if(sProductEntity.getPrice_type() == 1){
                    	singlePrice =  jsonObject.getDouble("price");
                    }else if(sProductEntity.getPrice_type() == 2){
                    	singlePrice =  jsonObject.getDouble("price") * rate;
                    }
                }else if (buy_status == 2){
                	singlePrice =  jsonObject.getDouble("spellPrice");
                }else if (buy_status == 4){
                	singlePrice =  jsonObject.getDouble("rushPrice");
                }

                
                logger.info("支付状态（payment_status）：{}，收、发货状态（receiving_status）：{}",payment_status);
                
                if (payment_status == 1) {
                    status = ProductStatusList.DELIVERY_STATUS;     //待发货
                    isCancelOrder = 0;
                    isReminding = 1;
                    isLogistics = 0;
                    isPayment = 0;
                    isRefund = 1;
                    ApplyRefundEntity applyRefund = applyRefundDao.findByOutTradeNo(order_id,shopsId);
                    if (PublicUtil.isNotEmpty(applyRefund)) {
                        //默认2：审核中，1：通过，0：拒绝
                        int refundStatus = applyRefund.getStatus();
                        if (refundStatus == 2) {
                            isRefund = 2;       //2：已申请退款
                        } else if (refundStatus == 1) {
                            isRefund = 3;       //3：已退款，
                        } else if (refundStatus == 0) {
                            isRefund = 4;       //4：拒绝退款
                        }
                    }
                } else if (payment_status == 2) {
                    isCancelOrder = 0;
                    isCollectGoods = 1;
                    isLogistics = 1;
                    isReminding = 0;
                    isRefund = 0;
                    status = ProductStatusList.UNCOLLECTED_GOODS_STATUS;     //待收货
                    isDeletion = ProductStatusList.DEL_NO;
                    isPayment = 0;
                }else if (payment_status == 3) {
                    isCancelOrder = 0;
                    status = ProductStatusList.RECEIVED_STATUS;     //已收货
                    isDeletion = ProductStatusList.DEL_YES;
                    isPayment = 0;
                } else if (payment_status == -1) {
                    isCancelOrder = 0;
                    isPayment = 0;
                    status = ProductStatusList.CANCEL_ORDER_STATUS;     //已取消订单
                }else{
                	isCancelOrder = 0;
                	status = ProductStatusList.PENDING_STATUS;     //待付款
                	isPayment = 1;
                }
                
                int num  = sOrderExtraEntity.getNum();
                
                totalPrice = totalPrice + singlePrice * num;
                
                //参数封装
                ProductStatusList productStatus = new ProductStatusList();
                productStatus.setId(sProductEntity.getId());
                productStatus.setHeadimg(sProductEntity.getHeadimg());
                productStatus.setName(sProductEntity.getName());
                productStatus.setNum(sOrderExtraEntity.getNum());
                productStatus.setPrice(singlePrice);
                productStatus.setStatus(status);
                productStatus.setIsDeletion(isDeletion);
                productStatus.setDate(sProductEntity.getCreated_time());
                productStatus.setAppraiseStatus(sProductEntity.getAppraisestatus());
                productStatus.setOrder_id(sProductEntity.getOrder_id());
                productStatus.setAttr(jsonAttrs);
                productStatus.setOutTradeNo(sProductEntity.getOut_trade_no());
              //按钮状态码
                productStatus.setIsCancelOrder(isCancelOrder);
                productStatus.setIsPayment(isPayment);
                productStatus.setIsReminding(isReminding);
                productStatus.setIsLogistics(isLogistics);
                productStatus.setIsCollectGoods(isCollectGoods);
                productStatus.setIsRefund(isRefund);
                productStatus.setAppraiseStatus(appraise_status);//评价状态
                
                productLists.add(productStatus);
                //当购买方式不是组合购买时，需要重新计算最终总价

            }
            //将循环后的列表放入店铺的特定字段中
            shopsInfo.setOrderList(productLists);
            //按钮状态码
            shopsInfo.setIsCancelOrder(isCancelOrder);
            shopsInfo.setIsPayment(isPayment);
            shopsInfo.setIsReminding(isReminding);
            shopsInfo.setIsLogistics(isLogistics);
            shopsInfo.setIsCollectGoods(isCollectGoods);
            shopsInfo.setIsRefund(isRefund);
            shopsInfo.setOut_trade_no(out_trade_no);
            shopsInfo.setOrder_id(order_id);
            shopsInfo.setPay_no(pay_no);
            if(buy_status != 5 ){
            	shopsInfo.setTotal_price(totalPrice);
            }else{
            	shopsInfo.setTotal_price(total_price * 1.0 / 100);
            }
            shopsInfo.setStatus(status);
            shopsInfo.setAppraiseStatus(appraise_status);
            shopsLocationList.add(shopsInfo);
    	}
        sProductEntities.clear();
    }
        return new Ajax(shopsLocationList);
    }


    @ApiOperation(value = "已完成的订单删除", notes = "已完成的订单逻辑删除",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "hideOrder", method = RequestMethod.POST)
    public Ajax hideOrder(@RequestBody CommodityReq commodityReq) throws Exception {

        logger.info("客户端请求数据（store/api/hideOrder）：{}", commodityReq.toString());
        String token = commodityReq.getToken();
        int user_id = 18;

        //需要删除的订单id
        int id = commodityReq.getId();

        //根据订单ID查询 该订单是否已经完成交易
        int i = sOrderDao.queryOrderIsFinished(id);
        if (i <= 0) throw new Exception("该订单未完成交易，请完成交易后再点击进行删除！");

        //修改 隐藏状态
        sOrderDao.changeHideStatus(id);

        return new Ajax("删除成功");
    }

//  这里订单取消需要进行拆分
    @ApiOperation(value = "取消订单", notes = "取消订单",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "cancelOrder", method = RequestMethod.POST)
    public Ajax cancelOrder(@RequestBody CommodityReq commodityReq) throws Exception {

        logger.info("客户端请求数据（store/api/cancelOrder）：{}", commodityReq.toString());

        //token认证
        UsersEntity userInfo = auth(commodityReq.getToken());
        int user_id = userInfo.getId();

        //需要取消的订单id
        int id = commodityReq.getId();

        //1：取消订单
        sOrderDao.changeOrderStatusById(-1, id);


        return new Ajax("订单取消成功");
    }


	@SuppressWarnings("unchecked")
	@ApiOperation(value = "添加收藏、取消收藏", notes = "添加收藏、取消收藏",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "collectionOperation", method = RequestMethod.POST)
    public Ajax addCollection(@RequestBody CollectionOperation collection) throws Exception {

        logger.info("客户端请求数据（store/api/collectionOperation）：{}", collection.toString());

        //token认证
        UsersEntity userInfo = auth(collection.getToken());
        int user_id = userInfo.getId();

        int status = collection.getStatus();

        List<Integer> ids = collection.getIds();//店铺商品ID数组
        
        int flag = 0;

        //参数封装
        JSONObject json = new JSONObject();
        for(int id : ids){
	        if (status == 1) {
	            RelCollectionEntity relCollectionEntity = relCollectionDao.queryByUserIdAndProductId(user_id, id);
	            if (relCollectionEntity != null){
	            	flag ++;
	            	continue;
	            }
	
	            //添加到收藏夹
	            relCollectionDao.insert(user_id, id);
	
	            json.put("desc", "收藏成功");
	            json.put("status", "1");
	        } else {
	
	            relCollectionDao.delByProductId(user_id, id);
	
	            json.put("desc", "取消成功");
	            json.put("status", "0");
	        }
        }
        if(flag == ids.size()) throw new Exception("该商品已经收藏过了");
     
        return new Ajax(json);
    }


    @ApiOperation(value = "收藏夹列表", notes = "收藏夹列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CollectionListResp.class)
    @ResponseBody
    @RequestMapping(value = "getCollectionList", method = RequestMethod.POST)
    public Ajax getCollectionList(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/getCollectionList）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();

        List<CollectionListResp> productLists = sProductDao.queryCollectionByUserId(user_id);

        logger.info("服务端返回数据（store/api/getCollectionList）：{}", productLists);
        return new Ajax(productLists);
    }

    @ApiOperation(value = "我的话题列表", notes = "我的话题列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = TbTopic.class)
    @ResponseBody
    @RequestMapping(value = "getMyTopicList", method = RequestMethod.POST)
    public Ajax getMyTopicList(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/getCollectionList）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();
        List<TbTopic> topicList = tbTopicDao.topicListByPublish(user_id);
        List<TbTopic> topicListNew = new ArrayList<>();
        for(TbTopic tbTopic : topicList){
			String jsonArrayStr2 = tbTopic.getThumbnailList();
			jsonArrayStr2 = jsonArrayStr2.replace(" ", "").substring(1, jsonArrayStr2.replace(" ", "").length()-1);
			String[] strArr = jsonArrayStr2.split(",");
			List<Integer> list = new ArrayList<>();
			for(String str :  strArr){
				list.add(Integer.valueOf(str));
			}
			tbTopic.setThumbnail(list);
			topicListNew.add(tbTopic);
        }
        logger.info("服务端返回数据（store/api/getCollectionList）：{}", topicList);
        return new Ajax(topicListNew);
    }

    @ApiOperation(value = "收藏夹删除", notes = "收藏夹删除",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delCollection", method = RequestMethod.POST)
    public Ajax delCollection(@RequestBody DelCollection delcollection) throws Exception {

        logger.info("客户端请求数据（store/api/delCollection）：{}", delcollection.toString());

        //token认证
        UsersEntity userInfo = auth(delcollection.getToken());
        int user_id = userInfo.getId();

        List<Integer> ids = delcollection.getIds();
        if (ids.size() <= 0) throw new Exception("未选择需要删除的收藏夹商品！");

        String id = StringUtils.strip(ids.toString(), "[]");

        logger.info("需要删除收藏夹的商品id集合：{}", id);

        relCollectionDao.delByIds(id);

        return new Ajax("删除成功");
    }


    @ApiOperation(value = "我的足迹、浏览记录删除", notes = "我的足迹、浏览记录删除",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delMyFootprint", method = RequestMethod.POST)
    public Ajax delMyFootprint(@RequestBody DelMyFootprint delMyFootprint) throws Exception {

        logger.info("客户端请求数据（store/api/delMyFootprint）：{}", delMyFootprint.toString());

        //token认证
        UsersEntity userInfo = auth(delMyFootprint.getToken());

        List<Integer> ids = delMyFootprint.getIds();
        if (ids.size() <= 0) throw new Exception("未选择需要删除浏览过的商品！");

        String id = StringUtils.strip(ids.toString(), "[]");

        logger.info("需要删除浏览过的商品id集合：{}", id);

        relFootprintDao.delByIds(id);

        return new Ajax("删除成功");
    }


    @ApiOperation(value = "我的足迹、浏览记录列表", notes = "我的足迹、浏览记录列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = MyFootprintListResp.class)
    @ResponseBody
    @RequestMapping(value = "getMyFootprint", method = RequestMethod.POST)
    public Ajax getMyFootprint(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/getMyFootprint）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();

        List<MyFootprintListResp> productLists = sProductDao.queryFootprintByUserId(user_id);

        logger.info("服务端返回数据（store/api/getMyFootprint）：{}", productLists);
        return new Ajax(productLists);
    }


    @ApiOperation(value = "我的足迹、浏览记录添加", notes = "我的足迹、浏览记录添加",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "addMyFootprint", method = RequestMethod.POST)
    public Ajax addMyFootprint(@RequestBody ProductIdReq apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/addMyFootprint）：{}", apiRequest.toString());

        //token认证
        UsersEntity userInfo = auth(apiRequest.getToken());
        int user_id = userInfo.getId();

        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        relFootprintDao.removalInsert(user_id, apiRequest.getId(),today);

        return new Ajax("");
    }


    @ApiOperation(value = "优惠券列表", notes = "优惠券列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Coupon.class)
    @ResponseBody
    @RequestMapping(value = "getCouponList", method = RequestMethod.POST)
    public Ajax getCouponList(@RequestBody CouponReq couponReq) throws Exception {

        logger.info("客户端请求数据（store/api/getCouponList）：{}", couponReq.toString());

        //token认证
        UsersEntity userInfo = auth(couponReq.getToken());
        int user_id = userInfo.getId();

        List<Coupon> sCouponEntities = sCouponDao.queryByType(user_id, couponReq.getStatus());

        logger.info("服务端返回数据（store/api/getCouponList）：{}", sCouponEntities);
        return new Ajax(sCouponEntities);
    }


    @ApiOperation(value = "优惠券领取", notes = "优惠券领取",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "receiveCoupon", method = RequestMethod.POST)
    public Ajax receiveCoupon(@RequestBody ReceiveCouponReq receiveCoupon) throws Exception {

        logger.info("客户端请求数据（store/api/receiveCoupon）：{}", receiveCoupon.toString());

        //token认证
        UsersEntity userInfo = auth(receiveCoupon.getToken());
        int user_id = userInfo.getId();
        int shops_id = receiveCoupon.getShops_id();

        Integer exist = relCouponDao.isExist(receiveCoupon.getId(), user_id);
        if (exist != null) throw new Exception("你已经领取过了，请购买商品进行使用");

        relCouponDao.insert(receiveCoupon.getId(), user_id,shops_id);
//        throw new Exception("你已经领取过了");

        return new Ajax("领取成功");
    }


    @ApiOperation(value = "优惠券删除", notes = "优惠券删除",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delCoupon", method = RequestMethod.POST)
    public Ajax delCoupon(@RequestBody DelCoupon delCoupon) throws Exception {

        logger.info("客户端请求数据（store/api/delCoupon）：{}", delCoupon.toString());

        //token认证
        UsersEntity userInfo = auth(delCoupon.getToken());

        List<Integer> ids = delCoupon.getIds();
        if (ids.size() <= 0) throw new Exception("未选择需要删除的优惠券！");

        String id = StringUtils.strip(ids.toString(), "[]");

        logger.info("需要删除的优惠券id集合：{}", id);

        relCouponDao.delByIds(id);

        return new Ajax("删除成功");
    }


    // TODO 获取目录列表
    @ApiOperation(value = "获取文件目录列表", notes = "获取文件目录列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "getFolderList", method = RequestMethod.POST)
    public Ajax getFolderList(@RequestBody FolderList folderList) throws Exception {

        logger.info("客户端请求数据（store/api/getFolderList）：{}", folderList.toString());

        String cosPath = PublicHandleUtils.getCosPath(cosPaths, folderList.getStatus());

        cosService.load();
        String folderLists = cosService.getFolderList(cosPath);
        cosService.getCosClient().shutdown();

        JSONObject jsonObject = JSONObject.parseObject(folderLists);
        JSONObject data = jsonObject.getJSONObject("data");

        logger.info("获取状态码（" + folderList.getStatus() + "）的目录列表：{}", data);
        return new Ajax(data);
    }

    @ApiOperation(value = "获取优惠券列表", notes = "获取优惠券列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "coupon", method = RequestMethod.POST)
    public Ajax coupon(@RequestBody CouponPar couponPar) throws Exception {
    	 //token认证
        UsersEntity auth = auth(couponPar.getToken());
        int user_id = auth.getId();
	    List<PayParameter> payParameters = couponPar.getPayParameters();
	    Map<Integer, Integer> map = new HashMap<>();
	    int i = 0;
    
	    for(PayParameter payParameter : payParameters){
	    	int shops_id = payParameter.getShopsId();
	    	int price = 0;
	        //购买的数量
	        int num = payParameter.getNum();
	        int sku_index = payParameter.getSkuIndex();
	        int product_id = payParameter.getProductId();
	        //根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
	        JSONObject jsonObject2 = userHandleService.checkSKUBySKUIndex(sku_index, product_id);
	        if (PublicUtil.isEmpty(jsonObject2)) throw new Exception("无此SKU值");
	        //根据SKU的价格支付,,注意此处的价格改为商品SKU单价
	        price = (int) (jsonObject2.getDouble("price") * 100) * num;
	    	
	    	if(i == 0){
	    		map.put(shops_id, price);
	    	}else if(map.containsKey(shops_id)){
	    		price = price + price;
	    		map.put(shops_id, price);
	    	}
	    }
    
	    Set<Integer> set = map.keySet();
	    Object[] intarr =  set.toArray();
	    List<Coupon> queryCouponByMoney2 = new LinkedList<>();
	    for(int j = 0 ; j< map.size();j++){
	    	int shops_id = (int) intarr[j];
	    	int price = map.get(shops_id);
	    	List<Coupon> queryCouponByMoney = relCouponDao.queryCouponByMoney(user_id, shops_id, price);
	    	queryCouponByMoney2.addAll(queryCouponByMoney);
	    }
	    return new Ajax(queryCouponByMoney2);
    }
    
    @ApiOperation(value = "下单", notes = "下单",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "wxPay", method = RequestMethod.POST)
    public Ajax wxPay(@RequestBody WxPay wxPay, HttpServletRequest request) throws Exception {

        logger.info("客户端请求数据（store/api/wxPay）：{}", wxPay.toString());

        //token认证
        UsersEntity auth = auth(wxPay.getToken());
        int user_id = auth.getId();
        String openId = auth.getOpenid();

        //收货地址、购买状态码
        int address_id = wxPay.getAddressId();
        int buy_status = wxPay.getBuyStatus();   //1：单独购买，2：发起拼单 3：钱包充值 4：限时抢购 5:组合搭配购买6:
        //---------------------- 下单 ------------------

        JsPayResult results = null;

        logger.info("- - - - - - 正在支付的openId - - - - - - {}", openId);

        // 统一下单（单号）
        String out_trade_no = CommonUtils.createOutTradeNo();

        /**
         *  得到支付金额，及下单入库处理
         */
        JSONObject jsonObject = payHandler(buy_status, wxPay, user_id);
        String total_fee = jsonObject.getString("total_fee");   // 产品支付价格1分钱,用于测试,注意这里的单位为分
        // 添加优惠券折扣，需要先判断订单是否满足满减优惠，然后再让优惠券生效，然后可以判断订单金额与钱包余额的大小关系，
        // 来确定是否要通过微信支付购买，若余额不足才减去相应的金额调用支付接口
        String sOrderExtraIds = jsonObject.getString("sOrderExtraIds");
        System.out.println("下单的支付金额："+ total_fee);																						
        SortedMap<Object, Object> payMaps = new TreeMap<Object, Object>();
        //用于获取自增ID
        SOrderEntity orderEntity = new SOrderEntity();
        //拼单订单多传一个拼单创建人ID
        if(buy_status == WxPay.Combine_Order){
        	int initiatorId = wxPay.getInitiatorId();
        	String combine_sale_id = wxPay.getFlashSaleId();
        	int num = combineOrderDao.countByFlashSaleId(Integer.valueOf(combine_sale_id), user_id);
        	if(num > 0) throw new Exception("您已参与过该拼团，请不要重复参与！");
        	CombineSaleEntity combineSale = combineSaleDao.findById(Integer.valueOf(combine_sale_id));
        	List<CombineDetailsResp> list = combineOrderDao.queryByFlashSaleId(Integer.valueOf(combine_sale_id) , user_id);
        	int still_need = combineSale.getCombine_num() - list.size() -1;
        	if(still_need  < 0 ) throw new Exception("该拼团名额已满，请换一组拼团");
        	logger.info("在拼单时传入的拼单特别拼单参数："+combine_sale_id);
        	if(PublicUtil.judge(String.valueOf(initiatorId)) && PublicUtil.judge(String.valueOf(combine_sale_id))){
        		//将订单信息存入数据库中：out_trade_no 订单号；total_fee：金额；user_id
                sOrderDao.insertToOrderCombine(out_trade_no, user_id, 0, Integer.parseInt(total_fee),
                        1, buy_status, address_id,initiatorId,Integer.parseInt(combine_sale_id), orderEntity);
                logger.info("拼单订单（s_order表）主键ID：{}", orderEntity.getId());
        	}else{
        		throw new Exception("拼单创建人ID错误或拼单商品错误");
        	}
            
        }else {

            //将订单信息存入数据库中：out_trade_no 订单号；total_fee：金额；user_id
            sOrderDao.insertToOrder(out_trade_no, user_id, 0, Integer.parseInt(total_fee),
                    1, buy_status, address_id, orderEntity);

            logger.info("订单（s_order表）主键ID：{}", orderEntity.getId());
        }

        if (PublicUtil.isNotEmpty(sOrderExtraIds)) {

            logger.info("多个商品下单后（sOrderExtra表的ID）：{}", sOrderExtraIds);

            sOrderExtraIds = sOrderExtraIds.substring(0, sOrderExtraIds.length() - 1);

            //多个商品下单，则有多个记录，但只会有一个单号，将多个商品与一个单号关联起来
            sOrderExtraDao.updateOrderId(orderEntity.getId(), sOrderExtraIds);
        }
        
        // 处理支付返回数据
        if(buy_status == WxPay.Separate_Purchase){
	        //TODO 注意，只有当是普通商品的时候才会出现使用优惠券的情况，其他购买模式都不能有优惠券
	        List<Integer> couponIds = wxPay.getCouponIds();
	        if(PublicUtil.isNotEmpty(couponIds)){
		        String couponIdsStr = couponIds.toString();
		        couponIdsStr = couponIdsStr.substring(1, couponIdsStr.length()-1);
		        int allCoupon = relCouponDao.countCoupon(couponIdsStr);
		        
		        payMaps = pursePay(wxPay.getToken(), total_fee, 0, out_trade_no);
		        
		        //总金额（单位：分）
		        double money = auth.getMoney() * 1.0 / 100;
		        //订单金额小于钱包余额满足支付条件，使用钱包支付方式
		        if((int)money > (Integer.valueOf(total_fee) - allCoupon) ){
		        	//调用钱包支付处理逻辑
		        	logger.info("进入钱包下单支付逻辑：{}", total_fee);
		        	payMaps = pursePay(wxPay.getToken(), total_fee, allCoupon, out_trade_no);
		            List<Integer> ids = wxPay.getShoppingCartIds(); 
		            userHandleService.delByShoppingCartId(ids);
		        	return new Ajax(payMaps);
		        }else{
			        logger.info("支付钱数：{}", total_fee);
			        //真正生成支付数据，在下单的时候优先从钱包里面扣款，必须考虑支付的问题
			        payMaps = callPay(request, out_trade_no,String.valueOf(Integer.valueOf(total_fee)-allCoupon) , openId, results);
		        }
		        
	        }else{
	        	 // 不使用优惠券的情况
	        	 //总金额（单位：分）
		        double money = auth.getMoney() * 1.0 / 100;
		        //订单金额小于钱包余额满足支付条件，使用钱包支付方式
		        if((int)money > (Integer.valueOf(total_fee))){
		        	//调用钱包支付处理逻辑
		        	logger.info("进入钱包下单支付逻辑：{}", total_fee);
		        	payMaps = pursePay(wxPay.getToken(), total_fee, 0, out_trade_no);
		            List<Integer> ids = wxPay.getShoppingCartIds(); 
		            userHandleService.delByShoppingCartId(ids);
		        	return new Ajax(payMaps);
		        }else{
			        logger.info("支付钱数：{}", total_fee);
			        //真正生成支付数据，在下单的时候优先从钱包里面扣款，必须考虑支付的问题
			        payMaps = callPay(request, out_trade_no,String.valueOf(total_fee) , openId, results);
		        }
	        }

        }else if(buy_status == WxPay.Purse_Recharge){//钱包充值必须使用微信支付的渠道
	        logger.info("支付钱数：{}", total_fee);
	        //真正生成支付数据，钱包充值时,金额有一定的倍差
	        total_fee = String.valueOf(Integer.valueOf(total_fee)*100);
	        payMaps = callPay(request, out_trade_no,total_fee, openId, results);
	        
        }else {
       	 //总金额（单位：分）
	        double money = auth.getMoney() * 1.0 / 100;
	        //订单金额小于钱包余额满足支付条件，使用钱包支付方式
	        if((int)money > (Integer.valueOf(total_fee)) ){
	        	//调用钱包支付处理逻辑
	        	logger.info("进入钱包下单支付逻辑：{}", total_fee);
	        	payMaps = pursePay(wxPay.getToken(), total_fee, 0, out_trade_no);
	            List<Integer> ids = wxPay.getShoppingCartIds(); 
	            userHandleService.delByShoppingCartId(ids);
	        	return new Ajax(payMaps);
	        }else{
	        	
	        //生成微信支付数据，在下单的时候优先从钱包里面扣款，必须考虑支付的问题
	        payMaps = callPay(request, out_trade_no,total_fee, openId, results);
	        }
        }

        //将签名参数缓存，以便用户下单后不支付，再次支付用 （默认订单10分钟有效）
        JedisUtil.redisCache(out_trade_no, payMaps, 600);

        List<Integer> ids = wxPay.getShoppingCartIds(); 
        
        userHandleService.delByShoppingCartId(ids);

        logger.info("服务端返回参数（订单签名数据）：{}", payMaps);
        return new Ajax(payMaps);
    }
    
    
    // 处理真正发起支付的参数
    public SortedMap<Object, Object> callPay(HttpServletRequest request, String out_trade_no, String total_fee,String openId,JsPayResult results) throws Exception {
    	
        String spbill_create_ip = HttpReqUtil.getRemortIP(request);

        logger.info("客户端支付IP：{}", spbill_create_ip);

        String nonce_str = UUID.randomUUID().toString().replaceAll("-", ""); // 随机数据


        //下单签名参数组装
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", appid);
        parameters.put("mch_id", mch_id);
        parameters.put("nonce_str", nonce_str);
        parameters.put("body", "test");
        parameters.put("out_trade_no", out_trade_no);           //商户订单号
        parameters.put("total_fee", total_fee);                       //标价金额
        parameters.put("spbill_create_ip", spbill_create_ip);     //终端IP 192.168.189.2 spbill_create_ip
        parameters.put("notify_url", notifyUrl);        //通知地址
        parameters.put("trade_type", "JSAPI");      //交易类型
        parameters.put("openid", openId);

        String sign = WechatPayService.createSign(parameters);   //签名
        parameters.put("sign", sign);          //将签名put到对象中

        String requestXML = WechatPayService.getRequestXml(parameters);     ////签名并入service

        String result = HttpReqUtil.httpsRequest(unifiedOrder, "POST", requestXML);

        logger.info("返回<![CDATA[SUCCESS]]>格式的XML： " + result.toString());

        Map<String, String> map = new HashMap<String, String>();

        map = XmlUtil.xmlToMap(result);     //将xml转为map


        //获取时间戳
        String timeStamp = PublicHandleUtils.createTimeStamp();

        nonce_str = UUID.randomUUID().toString().replaceAll("-", "");

        results = new JsPayResult();
        results.setAppId(appid);
        results.setTimeStamp(timeStamp);
        results.setNonceStr(nonce_str);//直接用返回的
        results.setSignType("MD5");

        /**** prepay_id 2小时内都有效，再次支付方法自己重写 ****/
        results.setPackageStr("prepay_id=" + map.get("prepay_id"));


        /**** 用对象进行签名 ****/
        results.setResultCode("SUCCESS");

        //再次签名
        SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();
        payMap.put("appId", results.getAppId());
        payMap.put("nonceStr", results.getNonceStr());
        payMap.put("package", results.getPackageStr());
        payMap.put("signType", results.getSignType());
        payMap.put("timeStamp", results.getTimeStamp());
        String paySign = WechatPayService.createSign(payMap);

        logger.info("second sign = {}", paySign);

        results.setPaySign(paySign);


        //---------------将参数（封装）驼峰式命名传给客户端--------
        SortedMap<Object, Object> payMaps = new TreeMap<Object, Object>();
        payMaps.put("nonceStr", payMap.get("nonceStr"));
        payMaps.put("timeStamp", payMap.get("timeStamp"));
        payMaps.put("package", payMap.get("package"));
        payMaps.put("signType", "MD5");
        payMaps.put("paySign", paySign);
    	return payMaps;
    }

    public Integer callProcedure(int user_id, int flash_sale_id, String phone) {
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        int result = -5;
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("flash_sale_id", flash_sale_id);
        map.put("phone", phone);
        map.put("dateTime", dateTime);
        map.put("result", result);

        Integer call = usersDao.call(map);
        Integer ret = (Integer) map.get("result");

        return ret;
    }

    /**
     * 得到支付金额，及下单入库处理
     *
     * @param buy_status 1：单独购买，2：发起拼单 3：钱包充值 4：限时抢购
     * @param wxPay      客户端请求数据
     * @param user_id    用户id
     * @return
     */
    public JSONObject payHandler(int buy_status, WxPay wxPay, int user_id) throws Exception {

        //支付金额
        String total_fee = "9999";

        //用于 将多个商品与一个单号关联起来
        String sOrderExtraIds = "";

        if (buy_status == WxPay.Separate_Purchase) { //普通购买逻辑处理，目前使用默认skuindex为0，根据组合ID来查询
            //购买商品的数量、商品SKU属性等
            List<PayParameter> payParameters = wxPay.getPayParameters();

            if (PublicUtil.isNotEmpty(payParameters)) {
                JSONObject json = HandleSKU(payParameters,buy_status);
                total_fee = json.getIntValue("pay_price") + "";
                sOrderExtraIds = json.getString("sOrderExtraIds");
            } else {
                throw new Exception("请选择购买的商品属性");
            }
        }else if(buy_status == WxPay.Group_Sale){ // 组合购买逻辑处理，目前使用默认skuindex为0，根据组合ID来查询
            //购买商品的数量、商品SKU属性等
            List<PayParameter> payParameters = wxPay.getPayParameters();
            payParameters.removeAll(payParameters);
            int  groupId = wxPay.getGroupId();
            ShopsCommend shopsCommend = groupProductDao.queryById(groupId);
            String productIds = shopsCommend.getProduct_ids();
            StringBuffer productArr = new StringBuffer();
            productArr = productArr.append(productIds.substring(1, productIds.length()-1).replace(" ", ""));
            
            String[] idsArr = productArr.toString().split(",");
            if(PublicUtil.objIsEmpty(shopsCommend)){
            	throw new Exception("该组合下没有商品");
            }
            int  shopsId = shopsCommend.getShops_id();
            for(String productId : idsArr ){
            	PayParameter payParameter = new PayParameter();
            	payParameter.setNum(1);
            	payParameter.setProductId(Integer.valueOf(productId));
            	payParameter.setSkuIndex(0);
            	payParameter.setShopsId(shopsId);
            	payParameters.add(payParameter);
            }
            
            if (PublicUtil.isNotEmpty(payParameters)) {
                JSONObject json = HandleSKU(payParameters,buy_status);
//              total_fee = json.getIntValue("pay_price") + "";
                total_fee = shopsCommend.getGroup_price() + "";
                total_fee = total_fee.substring(0, total_fee.length()-2);
                sOrderExtraIds = json.getString("sOrderExtraIds");
            } else {
                throw new Exception("请选择购买的商品属性");
            }
        }else if (buy_status == WxPay.Purse_Recharge) { // 钱包充值逻辑处理
            int money = wxPay.getMoney();
            if (money <= 0) throw new Exception("充值的金额数错误");
            total_fee = money + "";
        } else if (buy_status == WxPay.Flash_Sale) {// 抢购逻辑处理
            String flash_sale_id = wxPay.getFlashSaleId();
            FlashSaleEntity flashSale = flashSaleDao.findById(Integer.valueOf(flash_sale_id));
            if (PublicUtil.isEmpty(flashSale)) throw new Exception("无效的抢购ID");

            if (flashSale.getStock() <= 0) throw new Exception("抢购商品库存不足");

            /**
             * 调用存储过程 实现秒杀
             */
            Integer result = callProcedure(user_id, Integer.valueOf(flash_sale_id), "10010");
            logger.info("调用存储过程后的返回值：{}", result);

            if (result == -1) {
                throw new Exception("你已经抢购过该商品了");
            } else if (result == 0) {
                throw new Exception("活动还未开始或已经结束");
            }

            //购买商品的数量、商品SKU属性等
            List<PayParameter> payParameters = wxPay.getPayParameters();

            if (PublicUtil.isNotEmpty(payParameters)) {
                JSONObject json = HandleSKU(payParameters,buy_status);
//                total_fee =  flashSale.getPrice()+ "";
                total_fee = json.getIntValue("pay_price") + "";
                sOrderExtraIds = json.getString("sOrderExtraIds");
            } else {
                throw new Exception("请选择购买的商品属性");
            }
        } else if (buy_status == WxPay.Combine_Order) { // 拼单购买逻辑处理
            logger.info("参与拼单 ....  ");

            int initiatorId  = wxPay.getInitiatorId();
            if (PublicUtil.isEmpty(initiatorId)) throw new Exception("无效的拼单主ID");
            
            String combine_sale_id = wxPay.getFlashSaleId();
            CombineSaleEntity combineSale = combineSaleDao.findById(Integer.valueOf(combine_sale_id));
            if (PublicUtil.isEmpty(combineSale)) throw new Exception("无效的拼单ID");

//            if (combineSale.getStock() <= 0) throw new Exception("拼单商品库存不足");

            //购买商品的数量、商品SKU属性等
            List<PayParameter> payParameters = wxPay.getPayParameters();

            if (PublicUtil.isNotEmpty(payParameters)) {
                JSONObject json = HandleSKU(payParameters,buy_status);
                total_fee = json.getIntValue("pay_price") + "";
//                total_fee =  
                sOrderExtraIds = json.getString("sOrderExtraIds");
            } else {
                throw new Exception("请选择购买的商品属性");
            }

        }

        //参数封装后返回
        JSONObject json = new JSONObject();
        json.put("total_fee", total_fee);
        json.put("sOrderExtraIds", sOrderExtraIds);

        return json;
    }

    public JSONObject HandleSKU(List<PayParameter> payParameters,int buy_status) throws Exception {

        logger.info("商品SKU：{}", payParameters.toArray());

        // 美元兑换人民币的汇率,由后台的系统配置进行管理
        int rate = systemConfigDao.queryRateBycode();
        //用于 将多个商品与一个单号关联起来
        String sOrderExtraIds = "";

        //如果是购买的多件商品，则用于累计计算总共的支付金额
        int pay_price = 0;
        Map<Integer, String> payNoMap = new HashMap<>();

        int i = 0;
        String pay_no = new String();
        
        for (PayParameter payParameter : payParameters) {
        	
            //SKU索引id
            int sku_index = payParameter.getSkuIndex();

            //优惠券ID
//            int couponId = payParameter.getCouponId();

            //商品ID
            int product_id = payParameter.getProductId();

            int price = 0;
            int shops_id = payParameter.getShopsId();
            String product_name = "";
            
            

            //根据商品ID查询商品价格
            SProductEntity sProductEntity = sProductDao.queryById(product_id);
            if (PublicUtil.isNotEmpty(sProductEntity)) {
            	// TODO此处的价格显示还需要进行调整
//                price = sProductEntity.getPrice();
                product_name = sProductEntity.getName();
            }

            //购买的数量
            int num = payParameter.getNum();

            //根据 商品ID 查询 SKU 并根据SKU索引找到 用户购买的SKU值
            JSONObject jsonObject = userHandleService.checkSKUBySKUIndex(sku_index, product_id);
            if (PublicUtil.isEmpty(jsonObject)) throw new Exception("无此SKU值");
            
            if(buy_status == WxPay.Separate_Purchase) { //普通购买逻辑处理
	            int price_type = sProductEntity.getPrice_type();
	            if(price_type == 1){ //价格类型为1时为人民币
	            //根据SKU的价格支付,,注意此处的价格改为商品SKU单价
	            	price = (int) (jsonObject.getDouble("price") * 100) * num;
	            }else if(price_type == 2){//价格类型为2时为美元兑换后的人民币
	            	price = (int) (jsonObject.getDouble("price") * 100) * num * rate;
	            }
            }else if(buy_status == WxPay.Combine_Order) { // 拼单购买逻辑处理
            	price = (int) (jsonObject.getDouble("spellPrice") * 100) * num;
            }else if(buy_status == WxPay.Flash_Sale) {// 抢购逻辑处理
            	price = (int) (jsonObject.getDouble("rushPrice") * 100) * num;
            }
            
            //用于获取新增ID
            SOrderExtraEntity sOrderExtra = new SOrderExtraEntity();

            //在订单子表中插入支付流水号 TODO
            if(i==0){
            	pay_no = CommonUtils.createOutTradeNo();
            	payNoMap.put(shops_id, pay_no);
            	logger.info("新的支付方案测试效果");
            }else if(!payNoMap.containsKey(Integer.valueOf(shops_id))){
            	pay_no = CommonUtils.createOutTradeNo();
            	payNoMap.put(shops_id, pay_no);
            }
            
            /**
             * 商品入库 ,（处理部分：先将订单id至为0）
             */
            
            sOrderExtraDao.insertSOrderExtra(0, product_id, sku_index, product_name,
                    jsonObject.toJSONString(), price, num, 0,shops_id, pay_no,sOrderExtra);
            
            logger.info("商品入库订单子表实体类数据测试效果:"+sOrderExtra);
            
            // TODO 店铺的订单的插入
            sOrderDao.insertToShopsOrder(sOrderExtra.getId(), shops_id);	


            //获取所有商品下单的ID（sOrderExtra表主键ID）
            int id = sOrderExtra.getId();
            sOrderExtraIds = sOrderExtraIds + id + ",";

            //获取支付总价格
            pay_price = pay_price + price;
            
            i++;
        }

        JSONObject json = new JSONObject();
        json.put("pay_price", pay_price);
        json.put("sOrderExtraIds", sOrderExtraIds);

        return json;
    }
    
    @Transactional(rollbackFor=Exception.class)
    public SortedMap<Object, Object> pursePay(String token,String total_fee,int allCoupon,String out_trade_no) throws Exception {
    	SortedMap<Object, Object> payMaps = new TreeMap<Object, Object>();
        //token认证
        UsersEntity auth = auth(token);
        int user_id = auth.getId();
        if(!PublicUtil.judge(String.valueOf(allCoupon))){
        	allCoupon = 0;
        }
        	//总金额（单位：分）
            double money = auth.getMoney() * 1.0 / 100;
            //订单金额小于钱包余额满足支付条件，使用钱包支付方式
            if((int)money > (Integer.valueOf(total_fee) - allCoupon) ){
            	int newMoney = (int)money - (Integer.valueOf(total_fee) - allCoupon);
//            	usersDao.reduceMoney(newMoney, user_id);
//            	
//            	// 需要生成钱包消费记录
//            	walletDetailsDao.insertToWalletDetails(user_id, (Integer.valueOf(total_fee)- allCoupon) , (int)money, newMoney, 2);
            	
                //增加/减少 钱包总可用值及记录新增
                userHandleService.changeUserMoney(user_id, (Integer.valueOf(total_fee) - allCoupon), 2);
            	
            	//钱包支付成功，通知前端页面信息
	        	payMaps.put("pay_type", 1);
	        	payMaps.put("pay_result", "SUCCESS");
	        	payMaps.put("total_fee", Double.valueOf(total_fee)/100);
	        	payMaps.put("newMoney", Double.valueOf(newMoney)/100);
	        	payMaps.put("out_trade_no", out_trade_no);
            	
                // 正常的一次性下单购买成功流程
                SOrderEntity orderInfo = sOrderDao.findByOutTradeNo(out_trade_no);

                logger.info("orderInfo: {}", orderInfo.toString());
                int buy_status = orderInfo.getBuy_status();
                user_id = orderInfo.getUser_id();
                money = orderInfo.getPrice();
                int order_id = orderInfo.getId();
                if(buy_status == 2){
                    logger.info("拼单购买 ... ");
                    //TODO 此处拼单生成的订单记录还有待构建业务方案
                    SOrderEntity sOrderEntity =  sOrderDao.findByOutTradeNo(out_trade_no);
                    int initiator_id = sOrderEntity.getInitiator_id();
                    int combine_sale_id = sOrderEntity.getCombine_sale_id();
                    
                    CombineSaleEntity combineSale = combineSaleDao.findById(combine_sale_id);
                    // 拼单创建人与下单人是否一致，如果是通一个人就是发起拼单，否则就是参与拼单
                    if(initiator_id == user_id ){
                    	//发起拼单
                    	int still_need = combineSale.getCombine_num() -1 ;
                    	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 1,1);
                    	
                        //修改支付成功状态，将收货状态改为待发货
                        sOrderDao.changePayStatus(1, out_trade_no);
                        //此处会将订单子表的支付方式改为钱包支付，用于标识订单使用的付款方式
                        sOrderExtraDao.updateStatus(1, order_id,3,1);
                    }else{
                    	//参与拼单,此处要判断是否重复参与拼单
                    	List<CombineDetailsResp> list = combineOrderDao.queryByFlashSaleId(combine_sale_id, user_id);
                    	int still_need = combineSale.getCombine_num() - list.size() -1;
                    	logger.info("拼单购买剩余还需人数 ... "+still_need);
                    	if(still_need  < 0 ) throw new Exception("该拼团名额已满，请换一组拼团");
                    	if(still_need  == 0 ) {//拼单完成，启动拼单成功条件，更改拼单订单状态
                        	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 0,3);
                        	//还需要修改拼单主还需拼单人数
                        	combineOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,3);
                        	combineOrderDao.updateComplete(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 3);
                        	//  还需要修改订单状态
                        	for(CombineDetailsResp combineDetails : list){
                        		String outTradeNo = combineDetails.getOut_trade_no();
                        		sOrderExtraDao.updateCompleteStatus(3, outTradeNo);
                        	}
                        	
                            //修改支付成功状态，将收货状态改为待发货
                            sOrderDao.changePayStatus(1, out_trade_no);
                            //此处会将订单子表的支付方式改为钱包支付，用于标识订单使用的付款方式
                            sOrderExtraDao.updateStatus(1, order_id,3,3);
                    	}else{//拼单进行中，将子订单的状态转为进行中
                    		
                    	combineOrderDao.insert(combine_sale_id, user_id, out_trade_no, still_need, initiator_id, 0,1);
                    	//还需要修改拼单主还需拼单人数
                    	combineOrderDao.update(combine_sale_id, user_id, out_trade_no, still_need, initiator_id,1);
                    	
                        //修改支付成功状态，将收货状态改为待发货
                        sOrderDao.changePayStatus(1, out_trade_no);
                        //此处会将订单子表的支付方式改为钱包支付，用于标识订单使用的付款方式
                        sOrderExtraDao.updateStatus(1, order_id,3,1);
                    	}
                    }
                }else{  // 非拼单业务，进入正常流程
                	
                    //修改支付成功状态，将收货状态改为待发货
                    sOrderDao.changePayStatus(1, out_trade_no);
                    //此处会将订单子表的支付方式改为钱包支付，用于标识订单使用的付款方式
                    sOrderExtraDao.updateStatus(1, order_id,3,0);
                }
        }
            return payMaps;
    }

    protected UsersEntity auth(String token) throws Exception {
        UsersEntity userInfo = usersDao.queryByToken(token);
        if (userInfo == null) {
            throw new AuthException("token错误，认证失败", -9);
        }
        return userInfo;
    }

    @ApiOperation(value = "立即支付", notes = "立即支付",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "immediatePay", method = RequestMethod.POST)
    public Ajax notify(@RequestBody ImmediatePayReq immediatePayReq,HttpServletRequest request) throws Exception {

        logger.info("客户端请求数据（store/api/immediatePay）：{}", immediatePayReq.toString());
        //token认证
        UsersEntity auth = auth(immediatePayReq.getToken());
        int user_id = auth.getId();

        String outTradeNo = immediatePayReq.getOutTradeNo();
        JsPayResult results = null;
        SortedMap payMaps = (SortedMap) JedisUtil.getUnSerializeValue(outTradeNo);

        if (PublicUtil.isEmpty(payMaps)){
        //TODO 需要写个重新签名，保证可以再次购买,目前无法确定价格是否是正确的，需要进行联调才能确定，同时支付回调方法还需要重新修改一下   	

        String spbill_create_ip = HttpReqUtil.getRemortIP(request);
        logger.info("客户端支付IP：{}", spbill_create_ip);
        String nonce_str = UUID.randomUUID().toString().replaceAll("-", ""); // 随机数据
        String openId = auth.getOpenid();
        //这里需要对订单号进行拆分然后查找出需要的支付的钱
        String[] arr = outTradeNo.split("+");
        
        String out_trade_no = arr[0];
        String payNo = arr[1];
        if(PublicUtil.isEmpty(out_trade_no) || PublicUtil.isEmpty(payNo)){
        	throw new Exception("订单号错误");
        }
        
        int totalFee = sOrderDao.countOrderPrice(payNo);
        
	   	   //总金额（单位：分）
	       double money = auth.getMoney() * 1.0 / 100;
	       //订单金额小于钱包余额满足支付条件，使用钱包支付方式
	       if((int)money > totalFee ){
	       	//调用钱包支付处理逻辑
	       	logger.info("进入钱包下单支付逻辑：{}", totalFee);
	       	payMaps = pursePay(immediatePayReq.getToken(),String.valueOf(totalFee), 0, out_trade_no);
	       	return new Ajax(payMaps);
	       }else{
		        logger.info("支付钱数：{}", totalFee);
		        //真正生成支付数据，在下单的时候优先从钱包里面扣款，必须考虑支付的问题
		        payMaps = callPay(request, out_trade_no,String.valueOf(totalFee) , openId, results);
	       }
        
        }

        logger.info("从缓存中根据key：{} 获得的value：{}", outTradeNo, payMaps);

        String packages = (String) payMaps.get("package");

        // packages的值： "prepay_id=null";
        packages = packages.substring(packages.indexOf("=") + 1, packages.length());
        logger.info("处理过后的packages：{}", packages);

        if (packages.equals("null")) throw new Exception("未正确下单");


        logger.info("服务端返回数据：{}", payMaps);
        return new Ajax(payMaps);
    }


//    @Deprecated
//    @ApiOperation(value = "发起拼单", notes = "发起拼单",
//            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
//            response = Ajax.class)
//    @ResponseBody
//    @RequestMapping(value = "sponsors", method = RequestMethod.POST)
//    public Ajax sponsors(@RequestBody ApiRequest req) throws Exception {
//
//        logger.info("客户端请求数据（store/api/sponsors）：{}", req.toString());
//
//        List<ProductListResp> page = sProductDao.findByKeys("七");
//        //回调后查询拼单标识，如果是拼单则建立websocket连接，
//
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:kafka-producer.xml");
//        KafkaTemplate kafkaTemplate = ctx.getBean("kafkaTemplate", KafkaTemplate.class);
//
//        String msg = "中华人民共和国万岁!";
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.sendDefault(msg);
//        SendResult<String, String> result = future.get(10, TimeUnit.SECONDS);
//        logger.info("发送成功：{},{}", msg, result.getProducerRecord());
//
//
//        logger.info("服务端返回数据：{}", page);
//        return new Ajax(page);
//    }
    
    @ApiOperation(value = "拼单处理结果", notes = "拼单处理结果",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "combineResult", method = RequestMethod.POST)
    public Ajax combineResult(@RequestBody OrderDetailsReq req) throws Exception {

        logger.info("客户端请求数据（store/api/sponsors）：{}", req.toString());
        String outTradeNo = req.getOutTradeNo();
        JSONObject json = new JSONObject();
        CombineOrderEntity  combineOrder = combineOrderDao.queryCombineOrderByNo(outTradeNo);
        String initiator = combineOrder.getInitiator();
        String startTime = new String();
		String endTime = new String();
		int still_need = 5;
		int combine_num = 5;
        if(Integer.valueOf(initiator) == 1){
        	startTime = combineOrder.getStart_time();
        	endTime  = combineOrder.getEnd_time();
        	still_need = combineOrder.getStill_need();
        	combine_num = combineOrder.getCombine_num();
        }else{
        	int initiator_id = combineOrder.getInitiator_id();
        	int combine_sale_id = combineOrder.getCombine_sale_id();
        	CombineOrderEntity  initiatorCombineOrder = combineOrderDao.queryCombineOrderById(combine_sale_id, initiator_id);
        	startTime = initiatorCombineOrder.getStart_time();
        	endTime  = initiatorCombineOrder.getEnd_time();
        	still_need = initiatorCombineOrder.getStill_need();	
        	combine_num = initiatorCombineOrder.getCombine_num();
        }
        json.put("startTime", startTime);
        json.put("endTime", endTime);
        json.put("still_need", still_need);
        json.put("combine_num", combine_num);

        logger.info("服务端返回数据：{}",json.toString());
        return new Ajax(json);
    }


    @ApiOperation(value = "小程序登录", notes = "小程序登录",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public Ajax signIn(@RequestBody SignInReq signIn) throws Exception {

        logger.info("客户端请求数据（store/api/signIn）：{}", signIn.toString());

        //获取用户信息
        UserInfo userInfo = signIn.getUserInfo();
        String nickName = URLEncoder.encode(userInfo.getNickName(), "UTF-8");     
        userInfo.setNickName(nickName);
        
        
        
        String code = signIn.getCode();
        if (PublicUtil.isEmpty(code)) throw new Exception("code不能为空！");

        //根据请求code获取 openid
        String openId = wechatPayService.getOpenId(code);
        if (PublicUtil.isEmpty(openId)) throw new Exception("登录失败，code错误！");

        //生成 token
        String random = CommonUtils.getRandomStringByLength(6, 0);
        String token = MD5Util.MD5Encode(random, "UTF-8");


        //根据openid查询是否是新用户，
        int flag = usersDao.queryByOpenId(openId);

        if (flag == 0) {      //用户首次登录
            if (PublicUtil.isEmpty(userInfo)) throw new Exception("无法获取用户信息");

            //用户数据入库
            usersDao.insertToUsers(userInfo, openId, token);
        } else {
            //登录时修改token
            usersDao.updateToken(token, openId);
        }

        JSONObject json = new JSONObject();
        json.put("token", token);
        json.put("openId", openId);

        logger.info("服务端返回数据：{}", json);
        return new Ajax(json);
    }


    @ApiOperation(value = "个人中心", notes = "个人中心",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "userInfo", method = RequestMethod.POST)
    public Ajax userInfo(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/userInfo）：{}", apiRequest.toString());

        //token 认证
        UsersEntity auth = auth(apiRequest.getToken());
        int user_id = auth.getId();
        
        double money = auth.getMoney() * 1.0 / 100;

        JSONObject json = new JSONObject();
        json.put("money", money);
        json.put("goldCoin", 0);

        //活动模块,归属于个人中心布局下的活动广告页面
        List<Ads> adsList= adsDao.queryByCategoryId(SCategoryEntity.personal);
        // 根据个人浏览记录生成猜你喜欢
        List<MyFootprintListResp> productLists = sProductDao.queryGuessByUserId(user_id);
        json.put("adsList", adsList);
        json.put("guessLikeList", productLists);
        
        logger.info("服务端返回数据：{}", json);
        return new Ajax(json);
    }


    @ApiOperation(value = "钱包余额及流水记录", notes = "钱包余额及流水记录",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = WalletDetail.class)
    @ResponseBody
    @RequestMapping(value = "walletInfo", method = RequestMethod.POST)
    public Ajax walletInfo(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/walletInfo）：{}", apiRequest.toString());

        //token 认证
        UsersEntity auth = auth(apiRequest.getToken());

        List walletDetails = new ArrayList();

        //总金额（单位：分）
        double money = auth.getMoney() * 1.0 / 100;

        //根据user_id 查询钱包明细
        List<WalletDetailsEntity> walletDetailsEntities = walletDetailsDao.queryByUserId(auth.getId());
        if (PublicUtil.isNotEmpty(walletDetailsEntities)) {
            for (WalletDetailsEntity walletDetailsEntity : walletDetailsEntities) {
                WalletDetail walletDetail = new WalletDetail();

                String created_time = walletDetailsEntity.getCreated_time();
                created_time = created_time.substring(0, created_time.length() - 5);

                walletDetail.setMoney(walletDetailsEntity.getMoney() * 1.0 / 100);
                walletDetail.setRemainder(walletDetailsEntity.getNew_val() * 1.0 / 100);
                walletDetail.setStatus(walletDetailsEntity.getStatus());
                walletDetail.setCreateTime(created_time);
                walletDetails.add(walletDetail);
            }
        }


        //参数封装
        JSONObject json = new JSONObject();
        json.put("money", money);
        json.put("walletDetails", walletDetails);

        logger.info("服务端返回数据：{}", json);
        return new Ajax(json);
    }


    @ApiOperation(value = "消息中心", notes = "消息中心",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = WalletDetail.class)
    @ResponseBody
    @RequestMapping(value = "msgCenter", method = RequestMethod.POST)
    public Ajax msgCenter(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/msgCenter）：{}", apiRequest.toString());

        //token 认证
        UsersEntity auth = auth(apiRequest.getToken());


        List<MsgCenterEntity> msgCenters = msgCenterDao.queryByUserId(auth.getId());
        if (PublicUtil.isNotEmpty(msgCenters)) {
            for (MsgCenterEntity msgCenterEntity : msgCenters) {
                //时间处理
                String created_time = msgCenterEntity.getCreated_time();
                created_time = created_time.substring(0, created_time.length() - 5);
                msgCenterEntity.setCreated_time(created_time);
            }
        }

        logger.info("服务端返回数据：{}", msgCenters.toArray());
        return new Ajax(msgCenters);
    }


    @ApiOperation(value = "限时抢购列表", notes = "限时抢购列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = FlashSaleResp.class)
    @ResponseBody
    @RequestMapping(value = "getFlashSale", method = RequestMethod.POST)
    public Ajax getFlashSale(@RequestBody FlashSaleReq flashSaleReq) throws Exception {

        logger.info("客户端请求数据（store/api/getFlashSale）：{}", flashSaleReq.toString());

        //token 认证
        UsersEntity auth = auth(flashSaleReq.getToken());

        List list = new ArrayList();
        List<FlashSaleTimelinessEntity> timeliness = flashSaleTimelinessDao.query();
        if (PublicUtil.isNotEmpty(timeliness)) {
            for (FlashSaleTimelinessEntity flashSaleTimelinessEntity : timeliness) {

                int id = flashSaleTimelinessEntity.getId();

                List<FlashSale> flashSales = flashSaleDao.findByTimelinessId(id);

                String img = flashSaleTimelinessEntity.getImg();
                if (PublicUtil.isEmpty(img)) img = "";

                String desc = flashSaleTimelinessEntity.getDesc();
                if (PublicUtil.isEmpty(desc)) desc = "";


                //参数处理与封装
                FlashSaleResp flashSaleResp = new FlashSaleResp();
                flashSaleResp.setId(id);
                flashSaleResp.setFlashSales(flashSales);
                flashSaleResp.setImg(img);
                flashSaleResp.setDesc(desc);
                flashSaleResp.setStartTime(flashSaleTimelinessEntity.getStartTime());
                flashSaleResp.setEndTime(flashSaleTimelinessEntity.getEndTime());
                list.add(flashSaleResp);
            }
        }


        logger.info("服务端返回数据：{}", list.toArray());
        return new Ajax(list);
    }


    @ApiOperation(value = "消息删除", notes = "消息删除",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "delMsg", method = RequestMethod.POST)
    public Ajax delMsg(@RequestBody DelMsgReq delMsgReq) throws Exception {

        logger.info("客户端请求数据（store/api/delMsg）：{}", delMsgReq.toString());

        //token 认证
        UsersEntity auth = auth(delMsgReq.getToken());

        List<Integer> ids = delMsgReq.getIds();
        if (ids.size() <= 0) throw new Exception("未选择需要删除的消息");

        String id = StringUtils.strip(ids.toString(), "[]");

        logger.info("需要删除的消息id集合：{}", id);

        msgCenterDao.delByIds(auth.getId(), id);

        return new Ajax("删除成功");
    }


    @ApiOperation(value = "确认收货", notes = "确认收货",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "confirmDelivery", method = RequestMethod.POST)
    public Ajax confirmDelivery(@RequestBody ConfirmDelivery confirmDelivery) throws Exception {

        logger.info("客户端请求数据（store/api/confirmDelivery）：{}", confirmDelivery.toString());

        //token 认证
        UsersEntity auth = auth(confirmDelivery.getToken());
        int user_id = auth.getId();
        int shops_id = confirmDelivery.getShops_id();
        
        SOrderExtraEntity orderExtraEntity = sOrderExtraDao.queryByShopId(confirmDelivery.getOrderId(),shops_id);
        if (PublicUtil.isEmpty(orderExtraEntity)) throw new Exception("无效的订单ID");

        sOrderExtraDao.updateStatusByShopsId(SOrderEntity.RECEIVED_STATUS, confirmDelivery.getOrderId(),confirmDelivery.getShops_id(),1);
        
        //TODO  准备处理生成店铺会员
        List<Integer> list = sOrderDao.queryShopsById(orderExtraEntity.getOrderid());
        for(Integer id : list ){
        	if(sOrderDao.countShopsMember(user_id,id) == 0){
        		sOrderDao.insertToShopsMember(user_id,id);	
        	}
        }
        return new Ajax("确认成功");
    }


    @ApiOperation(value = "提醒发货", notes = "提醒发货",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "remindDelivery", method = RequestMethod.POST)
    public Ajax remindDelivery(@RequestBody ConfirmDelivery remindDelivery) throws Exception {

        logger.info("客户端请求数据（store/api/remindDelivery）：{}", remindDelivery.toString());

        //token 认证
        UsersEntity auth = auth(remindDelivery.getToken());

        return new Ajax("提醒成功");
    }


    @ApiOperation(value = "申请退款", notes = "申请退款",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "applyRefund", method = RequestMethod.POST)
    public Ajax refund(@RequestBody RefundReq refundReq) throws Exception {

        logger.info("客户端请求数据（store/api/applyRefund）：{}", refundReq.toString());

        //token 认证
        UsersEntity auth = auth(refundReq.getToken());

        int order_id = refundReq.getOrder_id();

        int shops_id = refundReq.getShops_id();
        // 这里需要修改订单，将订单进行拆分之后再分别申请退款
        SOrderExtraEntity orderExtra = sOrderExtraDao.queryByShopId(order_id, shops_id);

        if (PublicUtil.isEmpty(orderExtra)) throw new Exception("无此订单");
        if (orderExtra.getStatus() == SOrderEntity.UNCOLLECTED_GOODS_STATUS) throw new Exception("已发货，不能退款");
        if (orderExtra.getStatus() == SOrderEntity.RECEIVED_STATUS) throw new Exception("已收货，不能退款");

        //查询是否有对应的申请退款记录
        ApplyRefundEntity applyRefund = applyRefundDao.findByOutTradeNo(order_id,shops_id);
        if (PublicUtil.isNotEmpty(applyRefund)) {
            int status = applyRefund.getStatus();
            if (status == 2) {
                throw new Exception("商家已经收到您的退款申请，正在审核，请耐心等待。");
            } else if (status == 0) {
                throw new Exception(applyRefund.getRefuse_reason());
            } else if (status == 1) {
                throw new Exception("您的订单退款已成功受理，支付金额将返还到您的原支付账户，请注意查收。");
            }
        }

        //进入退款申请审批流程,最后一个参数为状态，默认为2
        applyRefundDao.insert(auth.getId(), order_id, refundReq.getName(),
                refundReq.getPhone(), refundReq.getRefundReasons(),shops_id,2);
        return new Ajax("申请成功，请耐心等待。");
    }


    @ApiOperation(value = "退款", notes = "退款",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public Ajax refund(@RequestBody Refund refund) throws Exception {

        logger.info("客户端请求数据（store/api/refund）：{}",  refund.toString());

        String token = refund.getToken();
        if (PublicUtil.isEmpty(token) || !token.equals(serverToken))
            throw new AuthException("token认证失败", -9);


        int order_id = refund.getOrder_id();

        int shops_id = refund.getShops_id();
        // 这里需要修改订单，将订单进行拆分之后再分别申请退款
        SOrderExtraEntity orderExtra = sOrderExtraDao.queryByShopId(order_id, shops_id);

        if (PublicUtil.isEmpty(orderExtra)) throw new Exception("无此订单");
        if (orderExtra.getStatus() == SOrderEntity.UNCOLLECTED_GOODS_STATUS) throw new Exception("已发货，不能退款");
        if (orderExtra.getStatus() == SOrderEntity.RECEIVED_STATUS) throw new Exception("已收货，不能退款");

        
        // 退款API接口，退款单号，订单金额，退款金额
        WechatRefundApiResult result = WechatUtil.wxRefund(order_id+"*"+shops_id,0.01, 0.01);

        logger.info("退款返回信息：{}", result);

        String msg = "";
        if (result.getResult_code().equals("SUCCESS")) {
            msg = "退款成功";
        } else {
            throw new Exception(result.getErr_code_des());
        }

        return new Ajax(msg);
    }


    @ApiOperation(value = "查看物流", notes = "查看物流",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "getOrderTraces", method = RequestMethod.POST)
    public Ajax getOrderTracesByJson(@RequestBody ApiRequest apiRequest) throws Exception {

        logger.info("客户端请求数据（store/api/getOrderTraces）：{}", apiRequest.toString());

        //查询物流轨迹
        String result = kdniaoTrackQueryAPI.getOrderTracesByJson(expCode, expNo);

        JSONObject jsonObject = JSONObject.parseObject(result);
        jsonObject.put("expressName", "圆通快递");

        logger.info("返回物流信息：{}", jsonObject);

        return new Ajax(jsonObject);
    }


    @ApiOperation(value = "获取手机验证码", notes = "获取手机验证码",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @RequestMapping(value = "getVerificationCode", method = RequestMethod.POST)
    public Ajax test(@RequestBody GetVerificationCode verificationCode) throws Exception {

        logger.info("客户端请求数据（store/api/getVerificationCode）：{}", verificationCode.toString());

        //token 认证
        UsersEntity auth = auth(verificationCode.getToken());


        //生成四位随机数值
        int code = (int) ((Math.random() * 9 + 1) * 1000);

        String msg = "“你的验证码为：" + code + "，请于5分钟内填写。如非本人操作，请忽略本短信。";
        SmsSingleSenderResult results = qcloudSmsService.qcloudSms(msg, verificationCode.getPhone());

        logger.info("获取手机验证码返回结果：{}", results.toString());

        if (results.result != 0) throw new Exception(results.errMsg);

        //判断key是否存在，存在即删除
        JedisUtil.existsDel(verificationCode.getToken());

        //设置缓存
        Long setnx = JedisUtil.setnx(verificationCode.getToken(), code + "", 120);


        return new Ajax("验证码已发送");
    }


    @ApiOperation(value = "一键生成供应商", notes = "一键生成供应商",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = Ajax.class)
    @ResponseBody
    @Transactional(rollbackFor=Exception.class)
    @RequestMapping(value = "generateSuppliers", method = RequestMethod.POST)
    public Ajax generateSuppliers(@RequestBody GenerateSuppliersReq generateSuppliersReq) throws Exception {

        logger.info("客户端请求数据（store/api/generateSuppliers）：{}", generateSuppliersReq.toString());

        //token 认证
        UsersEntity auth = auth(generateSuppliersReq.getToken());

        //判断验证码是否正确
        String code = generateSuppliersReq.getCode();

        //从缓存中取得数据
        String value = JedisUtil.getnx(generateSuppliersReq.getToken());

        if (PublicUtil.isEmpty(value) || !value.equals(code)) throw new Exception("验证码不正确");


        String phone = generateSuppliersReq.getPhone();
        String password = generateSuppliersReq.getPassword();

        //用户名生成
        String username = CommonUtils.getRandomStringByLength(4, 1) + auth.getId();

        password = MD5Util.getMessageDigest(password).toLowerCase();

        tbUserDao.insert(username, password, phone, 1, "供应商", 3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);

        return new Ajax(jsonObject);
    }

    //TODO  增加店铺相关信息，需要进行修改
    @ApiOperation(value = "订单详情", notes = "订单详情",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = OrderDetailsResp.class)
    @ResponseBody
    @RequestMapping(value = "orderDetails", method = RequestMethod.POST)
    public Ajax orderDetails(@RequestBody OrderDetailsReq orderDetailsReq) throws Exception {

        logger.info("客户端请求数据（store/api/orderDetails）：{}", orderDetailsReq.toString());

        //token 认证
        UsersEntity auth = auth(orderDetailsReq.getToken());

        //订单号
        String out_trade_no = orderDetailsReq.getOutTradeNo();
        //订单号
        int shops_id = orderDetailsReq.getShops_id();

        //详细地址
        String address = "";
        //用户名
        String username = "";
        //手机号
        String phone = "";
        //描述
        String desc = "";
        
        List<OrderDetails> orderDetails = sOrderDao.queryOrderDetail(out_trade_no,shops_id);
        List<ShopsLocation> shopsOrderDetailsList = new ArrayList<>();
        
        if (PublicUtil.isNotEmpty(orderDetails)) {
        		 List<OrderDetails> orderDetailsNew = sOrderDao.queryOrderDetailbyshops(out_trade_no, shops_id);
        		 List<OrderDetailsList> orderDetailsList = new ArrayList();
              	//取出订单的每个商品的店铺ID，进行判断，如果店铺ID一样就放到一起，若是不一样就新增一个店铺类
              	ShopsLocation shopsInfo = new ShopsLocation();
              	Double totalPrice = 0.00;
              	shopsInfo =  shopsDao.queryShopsByUserId(shops_id);
                //时间处理
                String purchase_time = new String();
                
                String pay_time  = new String();
                String pay_no  = new String();
              	
        		 for (OrderDetails orderDetail : orderDetailsNew) {
                 	
                     address = orderDetail.getProvince() + orderDetail.getCity() +
                             orderDetail.getArea() + orderDetail.getAddress();

                     phone = orderDetail.getPhone();

                     username = orderDetail.getUname();
                     pay_no = orderDetail.getPay_no();
                     int product_id = orderDetail.getProduct_id();
                     int buy_status = orderDetail.getBuy_status();
                     //购买状态码 -3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付
                     if (buy_status == 0) {
                         desc = "等待买家付款";
                     } else if (buy_status == 1) {
                         desc = "买家已付款";
                     } else if (buy_status == 1) {
                         desc = "买家已取消";
                     } else if (buy_status == -1) {
                         desc = "买家已取消";
                     }
                     

                     //时间处理
                      purchase_time = orderDetail.getPurchase_time();
                     purchase_time = purchase_time.substring(0, purchase_time.length() - 2);
                     if(buy_status >= 1){
                         pay_time = orderDetail.getPay_time();
                         pay_time = pay_time.substring(0, pay_time.length() - 2);
                     }


                     //json属性处理
                     String attrs = orderDetail.getAttrs();
                     JSONObject attr = JSONObject.parseObject(attrs);

                     //参数处理
                     OrderDetailsList detailsList = new OrderDetailsList();
                     detailsList.setProductName(orderDetail.getProduct_name());
                     detailsList.setPurchaseTime(purchase_time);
                     detailsList.setAttrs(attr);
                     detailsList.setNum(orderDetail.getNum());
                     detailsList.setPrice(orderDetail.getPrice() * 1.0 / 100);
                     detailsList.setBuy_status(orderDetail.getBuy_status());
                     detailsList.setDelivery_status(orderDetail.getDelivery_status());
                     detailsList.setHeadimg(orderDetail.getHeadimg());
                     detailsList.setPayTime(pay_time);
                     detailsList.setProduct_id(product_id);
                     orderDetailsList.add(detailsList);
                     totalPrice = totalPrice + orderDetail.getPrice() * 1.0 / 100 * orderDetail.getNum();
                 }
        		 shopsInfo.setOrderList(orderDetailsList);
        		 shopsInfo.setOut_trade_no(out_trade_no);
        		 shopsInfo.setPay_time(pay_time);
        		 shopsInfo.setPurchase_time(purchase_time);
        		 shopsInfo.setTotal_price(totalPrice);
        		 shopsInfo.setPay_no(pay_no);
        		 shopsOrderDetailsList.add(shopsInfo);
        	 }

        //参数封装
        OrderDetailsResp orderDetailsResp = new OrderDetailsResp();
        orderDetailsResp.setAddress(address);
        orderDetailsResp.setUsername(username);
        orderDetailsResp.setPhone(phone);
        orderDetailsResp.setShopsOrderDetailsList(shopsOrderDetailsList);
        orderDetailsResp.setDesc(desc);

        return new Ajax(orderDetailsResp);
    }

    @ApiOperation(value = "首页拼单商品列表", notes = "首页拼单商品列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CombineOrderResp.class)
    @ResponseBody
    @RequestMapping(value = "getCombineOrderList", method = RequestMethod.POST)
    public Ajax combineOrderList(@RequestBody CombineOrderReq combineOrderReq) throws Exception {

        logger.info("客户端请求数据（store/api/getCombineOrderList）：{}", combineOrderReq.toString());

        //token 认证
        UsersEntity auth = auth(combineOrderReq.getToken());
        
        List<CombineSaleEntity> list = combineSaleDao.query();

        logger.info("服务端返回数据（store/api/getCombineOrderList）：{}", list.toString());
        return new Ajax(list);
    }
    
    @ApiOperation(value = "拼单详情内拼单列表", notes = "拼单详情内拼单列表",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CombineOrderResp.class)
    @ResponseBody
    @RequestMapping(value = "combineOrderList", method = RequestMethod.POST)
    public Ajax combineOrder(@RequestBody CombineOrderReq combineOrderReq) throws Exception {

        logger.info("客户端请求数据（store/api/combineOrderList）：{}", combineOrderReq.toString());

        //token 认证
        UsersEntity auth = auth(combineOrderReq.getToken());
        Integer product_id = combineOrderReq.getProductId();
        if (PublicUtil.isEmpty(product_id)) throw new Exception("商品ID不正确");

        List combineOrderLists = new ArrayList();

        //查询特定的商品拼主的信息
        List<CombineSaleEntity> combineSaleEntities = combineSaleDao.queryByType(product_id);

        //拼单总人数
        int total_num = combineSaleEntities.size();

        if (PublicUtil.isNotEmpty(combineSaleEntities)){
            for (CombineSaleEntity combineSale : combineSaleEntities){

                if (combineSale.getInitiator() == 1) {  //1为拼主
                    CombineOrderList combineOrderList = new CombineOrderList();
                    combineOrderList.setHeadImg(combineSale.getHead_img());
                    combineOrderList.setNickname(combineSale.getNickname());
                    combineOrderList.setOutTradeNo(combineSale.getOut_trade_no());
                    combineOrderList.setStillNeedNum(combineSale.getStill_need());
                    combineOrderList.setStartTime(combineSale.getStartTime().getTime() / 1000);
                    combineOrderList.setEndTime(combineSale.getEndTime().getTime() / 1000);
                    combineOrderList.setUserId(combineSale.getUser_id());
                    combineOrderList.setCombineSaleId(combineSale.getId());
                    combineOrderLists.add(combineOrderList);
                }
            }
        }

        //参数封装
        CombineOrderResp combineOrderResp = new CombineOrderResp();
        combineOrderResp.setTotalNum(total_num);
        combineOrderResp.setCombineOrderList(combineOrderLists);

        logger.info("服务端返回数据（store/api/combineOrderList）：{}", combineOrderResp.toString());
        return new Ajax(combineOrderResp);
    }


    @ApiOperation(value = "拼单详情内拼单详情", notes = "拼单详情",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE,
            response = CombineDetailsResp.class)
    @ResponseBody
    @RequestMapping(value = "combineDetails", method = RequestMethod.POST)
    public Ajax combineDetails(@RequestBody CombineDetailsReq combineDetails) throws Exception {

        logger.info("客户端请求数据（store/api/combineDetails）：{}", combineDetails.toString());

        //token 认证
        UsersEntity auth = auth(combineDetails.getToken());
        Integer combine_sale_id = combineDetails.getFlashSaleId();
        Integer user_id = combineDetails.getUserId();

        if (PublicUtil.judge(String.valueOf(combine_sale_id))&&PublicUtil.judge(String.valueOf(user_id))){
        }else{
            throw new Exception("参数不正确");
        }

        //根据订单号查询拼单人数列表
        List<CombineDetailsResp> combineOrderEntities = combineOrderDao.queryByFlashSaleId(combine_sale_id, user_id);


        logger.info("服务端返回数据（store/api/combine）：{}", combineOrderEntities.toArray());
        return new Ajax(combineOrderEntities);
    }
    
    /**
     * 根据分类ID获取模块商品列表，只显示8条
     * @param typeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "plateProductList",method = RequestMethod.POST)
    @ApiOperation(value = "根据分类ID获取模块商品列表" ,response = PlatformPlateEntity.class)
    public Ajax<?> getPlateProductList(@RequestBody PlatformInfoReq platformInfoReq)throws Exception{
    	logger.info("客户端请求数据【store/api/TopproductList】,{}");
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    		 //token 认证
            UsersEntity auth = auth(platformInfoReq.getToken());
    		int typeId  = platformInfoReq.getTypeId();
    		JSONObject obj = new JSONObject();
    		List<PlatformPlateEntity> list = platformPlateDao.queryPlatformPlateList(typeId);
    		for(PlatformPlateEntity platformPlate : list){
    			int plateId = platformPlate.getId();
    			List<ProductReq> list2 = platformPlateDao.queryPlatformPlateProductList(plateId);
    			platformPlate.setPlateProductList(list2);
    		}
    		obj.put("PlateList", list);
    		result.setCode(0);
    		result.setData(obj);
    		logger.info("服务端返回数据（store/api/plateInfo）：{}", result.toString());
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setErrMsg("根据分类ID获取模块商品列表失败！");
    		return result;
		}
    }
    
    /**
     * 了解我们页面数据获取
     * @param typeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "plateInfo",method = RequestMethod.POST)
    @ApiOperation(value = "了解我们页面数据获取" ,response = PlatformPlateEntity.class)
    public Ajax<?> getPlateInfo(@RequestBody PlatformInfoReq platformInfoReq)throws Exception{
    	logger.info("客户端请求数据【store/api/plateInfo】,{}");
    	Ajax<JSONObject> result = new Ajax<>();
    	try {
    	 //token 认证
        UsersEntity auth = auth(platformInfoReq.getToken());
    	PlatformInfoEntity platformInfo = platformPlateDao.queryPlatformInfo();
    	PlatformInfoEntity2 platformInfo2 = new PlatformInfoEntity2();
    	String jsonArrayStr = platformInfo.getInfo().toString();
		jsonArrayStr = new String(jsonArrayStr.getBytes("ISO-8859-1"),"utf-8");
		JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
		platformInfo2.setInfo(jsonArray);
		logger.info("服务端返回数据（store/api/plateInfo）：{}", platformInfo2.toString());
    	return new Ajax(platformInfo2);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			result.setErrMsg("了解我们页面数据获取失败！");
    		return result;
		}
    }
}

