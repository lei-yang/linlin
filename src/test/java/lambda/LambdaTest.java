package lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {

    @Test
    public void testFun() {
        int x = 5;
        int y = 6;

        int c = add(x, y);
        System.out.println(c);
    }


    public int add(int x, int y) {
        return x + y;
    }

    @Test
    public void testThread() {
        // 原写法
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });
        thread.start();

        // lambda写法
        Thread threadLambda = new Thread(() -> System.out.println("hello lambda"));
        threadLambda.start();
    }

    @Test
    public void testForeach() {
        List<String> list = Arrays.asList(new String[]{"a", "c", "b", "e", "d"});
        // 匿名类写法
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.forEach((e) -> System.out.print(e + " "));

        // lambda写法
        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
        System.out.println("lambda写法");
        list.forEach((e) -> System.out.print(e + " "));
    }

    @Test
    public void testLowercase() {
        List<String> list = Arrays.asList(new String[]{"My", "Name", "Is", "Lei", "Yang"});
        list.forEach((e) -> System.out.print(e + " "));

        // 原写法
        List<String> cardList = new ArrayList<>();
        for (String card : list) {
            cardList.add(card.toLowerCase());
        }
        System.out.println("原写法");
        cardList.forEach((e) -> System.out.print(e + " "));
        cardList.clear();

        // lambda写法
        cardList = list.stream().map(card -> { return card.toLowerCase();}).collect(Collectors.toList());
        System.out.println("lambda写法");
        cardList.forEach((e) -> System.out.print(e + " "));
    }

    @Test
    public void testL() {
        List<String> list = new ArrayList<>();
        list.stream().forEach(finance -> {
            System.out.println("sss");
            System.out.println(finance == null);

        });
    }



}
