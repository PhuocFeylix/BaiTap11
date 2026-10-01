package vn.feylix.service;
import vn.feylix.entity.Rating_24162099; import java.util.List;
public interface RatingService_24162099 {List<Rating_24162099> findByBook(Long bookId);void save(Long userId,Long bookId,int rating,String text);}
