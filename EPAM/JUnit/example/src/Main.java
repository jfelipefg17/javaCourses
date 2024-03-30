//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public String print(int grade)
    {
        if(grade > 94)
            return "You got an A!";
        else if(grade > 83)
            return "You received a B";
        else if(grade > 73)
            return "You received a C";
        else
            return "You need to study more";
    }
}