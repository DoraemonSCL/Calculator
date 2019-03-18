import org.junit.Assert;

import static org.junit.Assert.*;

public class MainTest
{

    @org.junit.Test
    public void solve()
    {
        String sum = Main.Solve("11+22");
        Assert.assertEquals("11+22=33", sum);

        String sum1 = Main.Solve("22/11+2");
        Assert.assertEquals("22/11+2=4",sum1);

        StringBuilder stringBuilder = new StringBuilder() ;
        stringBuilder.append("i") ;
        stringBuilder.append(1) ;
        char newString = stringBuilder.charAt(stringBuilder.length() - 1 ) ;
        System.out.println(newString);
        System.out.println(stringBuilder);
        System.out.println("This is  a bee.");
    }
}