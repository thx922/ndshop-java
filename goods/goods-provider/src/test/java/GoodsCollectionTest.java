import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsCollection;
import org.ndshop.goods.service.IGoodsCollectionSer;
import org.ndshop.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 15:06]
 * @Description: [分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class GoodsCollectionTest {
    private static Logger logger = Logger.getLogger(GoodsCollectionTest.class);

    @Autowired
    private IGoodsCollectionSer goodsCollectionSer;

    @Test
    public void addGoodsCollection()throws SerException{

        String userId = "b75f0b79-7652-439e-84df-bbfd7da73f47";
        User user = new User();
        user.setId( userId );

        String goodsId = "3f7c296d-a7f3-4ac2-955c-3e5d77ad3a51";
        Goods goods = new Goods();
        goods.setId( goodsId );

        GoodsCollection goodsCollection = new GoodsCollection();
        goodsCollection.setUser( user );
        goodsCollection.setGoods( goods );
        goodsCollection.setCreateTime( LocalDateTime.now() );
        goodsCollection.setModifyTime( LocalDateTime.now() );

        goodsCollectionSer.save( goodsCollection );
    }

    @Test
    public void deleteGoodsCollection() throws SerException{

        String collectionId = "0d0c1c9a-95ee-4442-954a-76c7949e36dd";
        goodsCollectionSer.remove( collectionId );
    }
}