/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetest;

 
/*
 * @author admin
 */
import java.util.Stack;

public class ParseTest
{

    String input;
    Stack stack = new Stack();
    int cur;

    public void parse(String input)
    {
//////////////////////////////////////
        String str = input;

        char[] arr = str.toCharArray();
        // System.out.println(arr);

        for (int i = 0; i < str.length(); i++)
          {
                if (Character.isLetter(str.charAt(i)))
                  {
                    if (Character.isLowerCase(str.charAt(i)))
                      {
                        //replace current character with q
                        arr[i] = 'q';
                      }
                  }
                
                
                if (Character.isUpperCase(str.charAt(i)))//is UpperCase
                  {
                   // System.out.println(arr[i]);
                    arr[i] = 'n';
                  }
          }
        //  System.out.println(str);
        //System.out.println(arr);

        String b = new String(arr);
//////////////////////////////////////

        this.input = b;
        this.input = this.input.concat("$");
        cur = 0;
        try
          {
            P();
            if (this.input.charAt(cur) == '$')
              {
                System.out.println("Input  " + input + " is accepted");
              }
            else
              {
                System.out.println("error in input "
                        + input + " στη θέση " + (cur));
              }
          }
        catch (Exception e)
          {
            System.out.println("error in inpu "
                    + input
                    + " στη θέση " + (cur - 1));
          }
    }

    private void Advance() throws Exception
    {
        if (cur < input.length())
          {
            cur++;
          }
        System.out.println(stack + " Input is accepted"
                + input.substring(0, cur));
    }

    private void error() throws Exception
    {
        throw new Exception();
    }

    private void pushSymbol(String s)
    {
        stack.push(s);
        System.out.println(stack + " Call: " + s);
    }

    private void popSymbol()
    {
        String s = (String) stack.pop();
        System.out.println(stack + " Returns: " + s);
    }

    //  P -> S;  
    private void P() throws Exception
    {
        pushSymbol("P");

        S();
        if (input.charAt(cur) == ';')
          {
            Advance();
          }
        else
          {
            error();
          }
        popSymbol();
    }

//    S -> CS'
    private void S() throws Exception
    {
        pushSymbol("S");
        C();
        Stonos();

    }

//    S' -> *CS' | ε
    private void Stonos() throws Exception
    {
        pushSymbol("S'");
        if (input.charAt(cur) == '*')
          {
            Advance();
            C();
            Stonos();
          }
        else
           ; // ε
        popSymbol();
    }

//    C -> OC'
    private void C() throws Exception
    {
        pushSymbol("C");
        O();
        Ctonos();
    }

//    C' -> q O' C' | n O' C' | [ S ]  O' C' |ε
    private void Ctonos() throws Exception
    {
        pushSymbol("C'");
        if (input.charAt(cur) == 'q')
          {
            Advance();
            Otonos();
            Ctonos();
          }
        else if (input.charAt(cur) == 'n')
          {
            Advance();
            Otonos();
            Ctonos();
          }
        else if (input.charAt(cur) == '[')
          {
            Advance();
            S();
            if (input.charAt(cur) == ']')
              {
                Advance();
                Otonos();
                Ctonos();
              }

          }
        else
            ; // ε

        popSymbol();

    }

//    O -> AO' 
    private void O() throws Exception
    {
        pushSymbol("O'");
        A();
        Otonos();

    }

//    O' ->  ! O' | = n O
    private void Otonos() throws Exception
    {
        pushSymbol("O'");
        if (input.charAt(cur) == '!')
          {
            Advance();
            Otonos();
          }
        else if (input.charAt(cur) == '=')
          {
            Advance();
            if (input.charAt(cur) == 'n')
              {
                Advance();
                O();
              }
            else
              {
                error();
              }

          }

        popSymbol();

    }

//    A -> q | n | [ S ]
    private void A() throws Exception
    {
        pushSymbol("A'");
        if (input.charAt(cur) == 'q')
          {
            Advance();
          }
        else if (input.charAt(cur) == 'n')
          {
            Advance();
          }
        else if (input.charAt(cur) == '[')
          {
            Advance();
            S();
            if (input.charAt(cur) == ']')
              {
                Advance();
              }
          }
        else
          {
            error();
          }


        popSymbol();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        ParseTest p = new ParseTest();

        p.parse("[pli]=XX!;");
//        p.parse("[qqq]=nn!;");
//
        p.parse("[pli]*[eap]!;");
//        p.parse("[qqq]*[qqq]!;");

    }
}
