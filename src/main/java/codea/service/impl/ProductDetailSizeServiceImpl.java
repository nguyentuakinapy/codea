package codea.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import codea.dao.ColorDAO;
import codea.dao.GalleryDAO;
import codea.dao.ProductDetailDAO;
import codea.dao.ProductDetailSizeDAO;
import codea.entity.Gallery;
import codea.entity.ProductDetail;
import codea.entity.ProductDetailSize;
import codea.service.ProductDetailSizeService;
import codea.utils.CloudinaryUtils;

@Service
public class ProductDetailSizeServiceImpl implements ProductDetailSizeService {
	@Autowired
	ProductDetailSizeDAO detailSizeDAO;
	@Autowired
	ProductDetailDAO detailDAO;
	@Autowired
    GalleryDAO galleryDAO;
	@Autowired
	ColorDAO colorDAO;
    @Autowired
    CloudinaryUtils cloudinaryUtils;
	
	@Override
	@Transactional
	public void deleteSize(Integer id) {
	    ProductDetailSize size = detailSizeDAO.findById(id).get();
	    ProductDetail productDetail = size.getProductDetail();

	    // Đếm số size hiện tại
	    int currentSizeCount = productDetail.getSizes().size();
	    if (currentSizeCount <= 1) {
	    	var color = productDetail.getColor();
	    	List<Gallery> galleries = productDetail.getGalleries();
	    	if (galleries != null) {
                for (Gallery g : galleries) {
                    String publicId = cloudinaryUtils.extractPublicId(g.getImageUrl());
                    try {
                        cloudinaryUtils.deleteImage(publicId);
                    } catch (IOException e) {
                        throw new RuntimeException("Lỗi khi xoá ảnh trên Cloudinary", e);
                    }
                    galleryDAO.delete(g);
                }
                galleries.clear();
            }
	    	detailSizeDAO.delete(size);
	    	detailDAO.delete(productDetail);
	    	colorDAO.delete(color);
	    } else {
	        // Chỉ xóa size này
	        size.setProductDetail(null);
	        detailSizeDAO.delete(size);
	    }
	}

}
