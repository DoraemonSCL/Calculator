import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class Main
{

    private static String[] op = { "+", "-", "*", "/" };// Operation set
    public static void main(String[] args) {
        int foumula = Integer.parseInt(args[0]) ;
        if (foumula > 100  && foumula < 0 )
        {
            System.out.println("INPUT ERROR!!!");
            return  ;
        }
        for (int i = 0 ; i < foumula ; i++)
        {
            String question = MakeFormula();
            // System.out.println(question);
            String ret = Solve(question);
            String foumula_result = question + ret ;
            outputFile(foumula_result) ;
            System.out.println(ret);
        }

    }

    public static String MakeFormula()
    {
        StringBuilder build = new StringBuilder();
        int count = (int) (Math.random() * 2) + 1; //
        int start = 0;
        int number1 = (int) (Math.random() * 99) + 1; //
        build.append(number1);
        while (start <= count)
        {
            int operation = (int) (Math.random() * 3); //
            int number2 = (int) (Math.random() * 99) + 1;//
            build.append(op[operation]).append(number2);
            start ++;
        }
        return build.toString();
    }

    public static String Solve(String formula)
    {  //
        Stack<String> tempStack = new Stack<>();//
        Stack<Character> operatorStack = new Stack<>();//
        int len = formula.length(); //
        int k = 0;
        for(int j = -1; j < len - 1; j++)
        {
            char formulaChar = formula.charAt(j + 1);
            if(j == len - 2 || formulaChar == '+' || formulaChar == '-' || formulaChar == '/' || formulaChar == '*')
            {
                if (j == len - 2)
                {
                    tempStack.push(formula.substring(k));
                }
                else {
                    if(k < j)
                    {
                        tempStack.push(formula.substring(k, j + 1));
                    }
                    if(operatorStack.empty())
                    {
                        operatorStack.push(formulaChar); //if operatorStack is empty, store it
                    }else
                        {
                        char stackChar = operatorStack.peek();
                        if ((stackChar == '+' || stackChar == '-')
                                && (formulaChar == '*' || formulaChar == '/')){
                            operatorStack.push(formulaChar);
                        }else
                            {
                            tempStack.push(operatorStack.pop().toString());
                            operatorStack.push(formulaChar);
                            }
                    }
                }
                k = j + 2;
            }
        }
        while (!operatorStack.empty())
        { // Append remaining operators
            tempStack.push(operatorStack.pop().toString());
        }
        Stack<String> calcStack = new Stack<>();
        for(String peekChar : tempStack)
        { // Reverse traversing of stack
            if(!peekChar.equals("+") && !peekChar.equals("-") && !peekChar.equals("/") && !peekChar.equals("*"))
            {
                calcStack.push(peekChar); // Push number to stack
            }else
                {
                int a1 = 0;
                int b1 = 0;
                if(!calcStack.empty())
                {
                    b1 = Integer.parseInt(calcStack.pop());
                }
                if(!calcStack.empty())
                {
                    a1 = Integer.parseInt(calcStack.pop());
                }
                switch (peekChar)
                {
                    case "+":
                        calcStack.push(String.valueOf(a1 + b1));
                        break;
                    case "-":
                        calcStack.push(String.valueOf(a1 - b1));
                        break;
                    case "*":
                        calcStack.push(String.valueOf(a1 * b1));
                        break;
                    default:
                        calcStack.push(String.valueOf(a1 / b1));
                        break;
                }
            }
        }
        return formula + "=" + calcStack.pop();
    }

    public static void outputFile(String formule)
    {
        FileWriter fw = null;
        try {  //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f=new File("F:\\subject.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(formule);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
