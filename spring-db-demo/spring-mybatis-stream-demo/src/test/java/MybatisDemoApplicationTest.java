import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuansim.AppStart;
import com.yuansim.dao.CursorDao;
import com.yuansim.domain.Driver;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.cursor.defaults.DefaultCursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Iterator;
import java.util.List;

@SpringBootTest(classes = AppStart.class)
public class MybatisDemoApplicationTest {

    @Autowired
    private CursorDao cursorDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void test9(){

    }
    public void test8(){

    }

    @Test
    @Transactional
    public void test7(){
     /*   LogUtils.debug("TotalMemory", Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
        LogUtils.debug("FreeMemory", Runtime.getRuntime().freeMemory()/(1024*1024)+"M");
        LogUtils.debug("MaxMemory", Runtime.getRuntime().maxMemory()/(1024*1024)+"M");
        LogUtils.debug("UsedMemory", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(1024*1024)+"M");*/

        System.out.println("TotalMemory" +  Runtime.getRuntime().totalMemory()/(1024*1024)+"M");
        long start = System.currentTimeMillis();
        Cursor<Driver> stream = cursorDao.findByStream(0, 3000);
        stream.forEach(x-> {
            System.out.println("debug---");
            if(true){
                System.out.println(x);
            }
        });
        long end = System.currentTimeMillis();
        System.out.printf("耗时：" + (end - start));

    }
    @Test
    public void test6(){

        long start = System.currentTimeMillis();
        List<Driver> drivers = cursorDao.findByLimit(0, 3000);
        long end = System.currentTimeMillis();
        System.out.printf("耗时：" + (end - start));
    }
// -------------------  以上是内存测试 Before -Xmx3m
    @Test
    public void test5(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DefaultCursor<Driver> stream = (DefaultCursor<Driver>) sqlSession.getMapper(CursorDao.class).findByStream(9000000, 10);
        System.out.println( "流打开状态：" + stream.isOpen());
        System.out.println( "结果是否全部取完:" + stream.isConsumed());
        stream.forEach(x-> {
            System.out.println( "流打开状态：" + stream.isOpen());
            System.out.println( "结果是否全部取完:" + stream.isConsumed());
            System.out.println(x);
        });

        System.out.println( "流打开状态：" + stream.isOpen());
        System.out.println( "结果是否全部取完:" + stream.isConsumed());

        if(!stream.isOpen()){
            stream.close();
        }
        System.out.println( "流打开状态：" + stream.isOpen());
        System.out.println( "结果是否全部取完:" + stream.isConsumed());
    }


    @Test
    public void test1(){

        long start = System.currentTimeMillis();
        List<Driver> drivers = cursorDao.findByLimit(9000000, 10);
        long end = System.currentTimeMillis();
        System.out.printf("耗时：" + (end - start));

    }

    @Test
    public void test2(){
        Page<Object> objects = PageHelper.offsetPage(9000000, 10);
        long start = System.currentTimeMillis();
        List<Driver> drivers = cursorDao.findByPageHelper(objects);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    public void test3(){
        Page<Object> objects = PageHelper.startPage(900000, 10);
        long start = System.currentTimeMillis();
        List<Driver> drivers = cursorDao.findByPageHelper(objects);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    @Transactional
    public void test4(){

        long start = System.currentTimeMillis();
        Cursor<Driver> stream = cursorDao.findByStream(9000000, 10);

        System.out.println( "流打开状态：" + stream.isOpen());
        System.out.println( "" + stream.isConsumed());
        int currentIndex = stream.getCurrentIndex();
        System.out.println("游标："+ currentIndex);

        stream.forEach(x-> System.out.println(x));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}
