package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Maintenance;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.MaintenanceDao;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.repository.RecordDao;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.RecordService;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

@Service
public class RecordServiceImpl implements RecordService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RecordDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

<<<<<<< HEAD
	@Autowired
	private MaintenanceDao maintenanceDao;

=======
	// TODO ç™¼é€ä¿¡ä»¶çµ¦è²·å®¶å’Œè³£å®¶ï¼Œè¨‚å–®æˆç«‹PRODUCTåº«å­˜è®Šæ›´
	// TODO è³¼è²·æ•¸ > productåº«å­˜æ•¸ï¼Œç„¡æ³•è³¼è²·
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
	@Override
	public RecordRes create(Record record) {
	    // ç¡®è®¤å‚æ•°æ˜¯å¦æ­£ç¡®å¡«å†™
	    if (!StringUtils.hasText(record.getProductName()) ||
	        !StringUtils.hasText(record.getStatus()) ||
	        !StringUtils.hasText(record.getRecordType()) ||
	        record.getProductCount() <= 0 ||
	        record.getProductAmount() <= 0) {
	        logger.error("Invalid parameters provided for record creation.");
	        return new RecordRes(RtnCode.PARAM_ERROR);
	    }

	    try {
	        dao.save(record);

<<<<<<< HEAD
		// ¨ú±o°Ó«~¸ê°T
		Optional<Product> op = productDao.findById(record.getProductId());
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = op.get();
		// ½T»{ÁÊ¶R¼Æ¶q <= ®w¦s
		if (product.getInventory() < record.getProductCount()) {
			return new RecordRes(RtnCode.PRODUCT_IS_SHORTAGE);
		}

		// °Ó«~®w¦s - ÁÊ¶R¼Æ
		product.setInventory(product.getInventory() - record.getProductCount());

		try {
			productDao.save(product);
			dao.save(record);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
		}

		// TODO µo°e«H¥óµ¹½æ®a

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO µo°e«H¥óµ¹¶R®a(¨ú®ø¦¨¥\)©M½æ®a(«È¤á¨ú®ø­q³æ)
=======
	        // TODO: åœ¨è¿™é‡Œæ·»åŠ å‘é€é‚®ä»¶ç»™ä¹°å®¶å’Œå–å®¶çš„é€»è¾‘

	        logger.info("Record created successfully. ID: {}", record.getRecordId());
	    } catch (Exception e) {
	        logger.error("Error creating record: {}", e.getMessage(), e);
	        return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
	    }

	    return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO ç™¼é€ä¿¡ä»¶çµ¦è²·å®¶(å–æ¶ˆæˆåŠŸ)å’Œè³£å®¶(å®¢æˆ¶å–æ¶ˆè¨‚å–®)
	// TODO è¨‚å–®æˆç«‹PRODUCTåº«å­˜è®Šæ›´
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
	@Override
	public RecordRes cancel(int id) {
		// ç¢ºèªè¨‚å–®æ˜¯å¦å­˜åœ¨
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// å–å¾—è¨‚å–®è³‡è¨Š
		Optional<Record> op = dao.findById(id);

		// è¨‚å–®è³‡æ–™ç‚ºç©º
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// æ›´æ”¹è¨‚å–®ç‹€æ…‹(valid => false)
		Record record = op.get();
		record.setValid(false);

<<<<<<< HEAD
		// ¨ú±o°Ó«~¸ê°T
		Optional<Product> proOp = productDao.findById(record.getProductId());
		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// °Ó«~®w¦s + ÁÊ¶R¼Æ
		product.setInventory(product.getInventory() + record.getProductCount());

		// ±N§ó·sªº¸ê®Æ¦s¤JDB
		try {
			productDao.save(product);
			dao.save(record);
			// TODO µo°e«H¥óµ¹¶R®a©M½æ®a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CANCEL_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordRes shipping(int id) {
		// ½T»{­q³æ¬O§_¦s¦b
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// ¨ú±o­q³æ¸ê°T
		Optional<Record> op = dao.findById(id);

		// ­q³æ¸ê®Æ¬°ªÅ
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// §ó§ï­q³æª¬ºA(status => "¥X³f¤¤")
		Record record = op.get();
		if (record.getStatus().matches("¨ú®ø­q³æ")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		record.setStatus("¥X³f¤¤");

		// ±N§ó·sªº¸ê®Æ¦s¤JDB
=======
		// å°‡æ›´æ–°çš„è³‡æ–™å­˜å…¥DB
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
		try {
			dao.save(record);
			// TODO ç™¼é€ä¿¡ä»¶çµ¦è²·å®¶å’Œè³£å®¶
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_SHIPPING_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordRes completed(int id) {
		// ½T»{­q³æ¬O§_¦s¦b
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// ¨ú±o­q³æ¸ê°T
		Optional<Record> op = dao.findById(id);

		// ­q³æ¸ê®Æ¬°ªÅ
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// §ó§ï­q³æª¬ºA(status => "¤w§¹¦¨")
		Record record = op.get();
		if (record.getStatus().matches("¨ú®ø­q³æ")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		// ¨ú±o°Ó«~¸ê°T(¼W¥[°Ó«~¾P°â¼Æ)
		Optional<Product> proOp = productDao.findById(record.getProductId());

		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// ¨ú±o®ø¶OªÌ¸ê°T(¦©°£ÂI¼Æ)
		Optional<User> buyerOp = userDao.findById(record.getUserId());

		if (buyerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User buyer = buyerOp.get();

		// ¨ú±o½æ®a¸ê°T(¼W¥[ÂI¼Æ)
		Optional<User> sellerOp = userDao.findById(record.getSellerId());

		if (sellerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User seller = sellerOp.get();

		// §ó§ï­q³æª¬ºA
		record.setStatus("¤w§¹¦¨");

		// ­q³æ§¹¦¨«á¡A¹q°Ó©â¨ú3%¤âÄò¶O
		Maintenance maintenance = new Maintenance(record.getRecordId(), //
				(int) Math.floor(record.getProductAmount() * 0.07));

		// ¼W¥[°Ó«~¾P°â¼Æ
		product.setSaleCount(product.getSaleCount() + record.getProductCount());

		// ¦©°£®ø¶OªÌÂI¼Æ
		buyer.setPoints(buyer.getPoints() - record.getProductAmount());

		// ½æ®aÂI¼Æ¼W¥[(Á`ª÷ÃB-¤âÄò¶O)
		seller.setPoints(seller.getPoints() + (record.getProductAmount() - maintenance.getMaintenanceCost()));

		// ±N§ó·sªº¸ê®Æ¦s¤JDB
		try {
			dao.save(record);
			maintenanceDao.save(maintenance);
			productDao.save(product);
			userDao.save(buyer);
			userDao.save(seller);
			// TODO µo°e«H¥óµ¹¶R®a©M½æ®a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_COMPLETED_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordSearchRes delete(List<Integer> idList) {
		// ½T»{­q³æid¬O§_¦s¦b
		for (int id : idList) {
			if (!dao.existsById(id)) {
				return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
			}

		}

		// ¦s©ñ§R°£record¸ê®Æ
		List<Record> records = dao.findAllById(idList);

		try {
			dao.deleteAllById(idList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordSearchRes(RtnCode.RECORD_DELETE_FAILED);
		}

		return new RecordSearchRes(RtnCode.SUCCESSFUL, records);
	}

	@Override
	public RecordSearchRes getRecordInfoByUserId(int id) {
		// ç¢ºèªä½¿ç”¨è€…æ˜¯å¦å­˜åœ¨
		if (!userDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByUserId(id);

		// æœ‰å¯èƒ½æœƒæ²’æœ‰è³¼è²·æˆ–è³£å•†å“çš„äº¤æ˜“ç´€éŒ„->ä¸ç”¨é˜²recordListç‚ºç©ºï¼Œ
		// ä½†è¦çµ¦ä¸€å€‹ç©ºListï¼Œè®“ä»–ä¸æ˜¯null
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}

	@Override
	public RecordSearchRes getRecordInfoByProductId(int id) {
		// ç¢ºèªä½¿ç”¨è€…æ˜¯å¦å­˜åœ¨
		if (!productDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByProductId(id);

		// æœ‰å¯èƒ½æœƒæ²’æœ‰è³¼è²·æˆ–è³£å•†å“çš„äº¤æ˜“ç´€éŒ„->ä¸ç”¨é˜²recordListç‚ºç©ºï¼Œ
		// ä½†è¦çµ¦ä¸€å€‹ç©ºListï¼Œè®“ä»–ä¸æ˜¯null
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}
	@Override
	 public RecordSearchRes delete(List<Integer> idList) {
	  // ç¢ºèªè¨‚å–®idæ˜¯å¦å­˜åœ¨
	  for (int id : idList) {
	   if (!dao.existsById(id)) {
	    return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
	   }

	  }

	  // å­˜æ”¾åˆªé™¤recordè³‡æ–™
	  List<Record> records = dao.findAllById(idList);

	  try {
	   dao.deleteAllById(idList);
	  } catch (Exception e) {
	   logger.error(e.getMessage());
	   return new RecordSearchRes(RtnCode.RECORD_DELETE_FAILED);
	  }

	  return new RecordSearchRes(RtnCode.SUCCESSFUL, records);
	 }
}
