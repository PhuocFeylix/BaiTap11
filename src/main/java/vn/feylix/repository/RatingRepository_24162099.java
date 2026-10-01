package vn.feylix.repository;
import vn.feylix.entity.Rating_24162099; import java.util.List;
public interface RatingRepository_24162099 { List<Rating_24162099> findByBook(Long bookId); void saveOrUpdate(Rating_24162099 r); }
