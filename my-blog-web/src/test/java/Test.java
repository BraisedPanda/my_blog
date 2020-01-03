import com.braisedpanda.my.blog.commons.utils.FontUtils;

import java.awt.*;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 11:37
 **/
public class Test {

    public static void main(String[] args) {
        String str = "一般来说的话，天空的颜色是蓝色的；" +
                "  当然，也有其他的颜色，比如 橘黄色";
       String result =  FontUtils.DBChange(str);
        System.out.println(result);
    }

    public void testLambda(){

    }

}
