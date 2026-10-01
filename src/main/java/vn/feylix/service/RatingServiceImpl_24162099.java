package vn.feylix.service;
import vn.feylix.entity.Rating_24162099; import vn.feylix.repository.*; import java.util.List;
public class RatingServiceImpl_24162099 implements RatingService_24162099{
    private final RatingRepository_24162099 repo=new RatingRepositoryImpl_24162099();
    public List<Rating_24162099> findByBook(Long id){return repo.findByBook(id);}
    public void save(Long u,Long b,int r,String t){Rating_24162099 x=new Rating_24162099();x.setUserId(u);x.setBookId(b);x.setRating(r);x.setReviewText(t);repo.saveOrUpdate(x);}
}
