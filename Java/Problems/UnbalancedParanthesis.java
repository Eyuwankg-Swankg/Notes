/*
 * Remove Unbalaced Paranthesis 
 * 
 * 
 *  Input  : ((abc)((de))
    Output : ((abc)(de))  

    Input  : (((ab)
    Output : (ab) 
 * 
 */

import java.util.*;

public class UnbalancedParanthesis {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Character> stack = new ArrayList<Character>();
        ArrayList<Integer> position = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        sb.append(sc.next());
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch == ')' || ch == '(') {
                if (ch == '(') {
                    stack.add(ch);
                    position.add(i);
                } else {
                    if (stack.size() > 0) {
                        if (stack.get(stack.size() - 1) != '(') {
                            stack.add(ch);
                            position.add(i);
                        } else {
                            stack.remove(stack.size() - 1);
                            position.remove(position.size() - 1);
                        }
                    }
                }

            }
        }
        for (int i = 0; i < stack.size(); i++){
            sb.delete(position.get(i), position.get(i)+1);
        }
        System.out.print(sb.toString());
    }

}
