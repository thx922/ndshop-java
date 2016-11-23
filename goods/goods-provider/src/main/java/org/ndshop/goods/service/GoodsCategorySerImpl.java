package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import com.dounine.corgi.spring.rpc.Reference;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ike on 16-11-9.
 */
@Service
public class GoodsCategorySerImpl extends ServiceImpl<GoodsCategory , GoodsCategoryDto> implements IGoodsCategorySer {
    private static Logger logger = Logger.getLogger(GoodsCategorySerImpl.class);

    @Reference
    IUserSer userSer;

    @Transactional
    @Override
    public void addCategory(GoodsCategory goodsCategory) throws SerException {
        goodsCategory.setName(  goodsCategory.getName() );
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        save(  goodsCategory );


    }

    @Transactional
    @Override
    public void updateCategory( GoodsCategory goodsCategory  ) throws SerException{
        if( goodsCategory != null ){
            String cateoryId = goodsCategory.getId();
            String categoryName = goodsCategory.getName();

            GoodsCategory gc = findById( cateoryId );
            goodsCategory.setModifyTime( LocalDateTime.now() );
            goodsCategory.setId( cateoryId );
            goodsCategory.setName( categoryName );
            update( goodsCategory );
            logger.info( JSON.toJSONString( goodsCategory ) );
        }
    }

    @Transactional
    @Override
    public void deleteCategory( GoodsCategory goodsCategory ) throws SerException{
        String cateoryId =goodsCategory.getId();
        goodsCategory = findById( cateoryId );
        if( goodsCategory != null ){
            remove( cateoryId );
        }else{
            logger.info(JSON.toJSONString(goodsCategory));
        }

    }

    @Transactional
    @Override
    public void addBatchCategory(List<String> categoryName) throws SerException{
        for( String str : categoryName){
            GoodsCategoryDto dto = new GoodsCategoryDto();
            Condition c = new Condition("name",DataType.STRING , str );
            c.setRestrict(RestrictionType.EQ);
            dto.getConditions().add( c );
            List<GoodsCategory> gc = findByCis( dto );

            if( gc== null || gc.size()==0 ){
                GoodsCategory gct = new GoodsCategory();
                gct.setName( str );
                save( gct );
                logger.info(JSON.toJSONString( gct ) );
            }
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findCategoryByFirstCategory (String firstCategoryName ) throws  SerException{
//        User user =userSer.findByUsername("liguiqin");
        Condition condition = new Condition("name", DataType.STRING ,firstCategoryName);
        condition.setRestrict(RestrictionType.LIKE);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( condition );
        dto.setLimit(2);
        dto.setPage(1);
        dto.setSorts(Arrays.asList("modifyTime"));
        List<GoodsCategory> goodCategory = findByCis( dto,true );
        logger.info( JSON.toJSONString(goodCategory) );

    }
}
