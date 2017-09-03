import java.net.PasswordAuthentication;
import java.util.Scanner;

import static java.util.Locale.CHINESE;

enum Teacher_Type
{
    CHINESE,ENGLISH,MATH
}
class Person
{
  protected String name;
  public String Get_Name() {return name;}
  public void Set_Name(String n) {name=n;}
  public Person(String n)
  {
      name=n;
  }
  public Person()
  {
      name=null;
  }
}

class Student extends Person
{
    private int Grade; //年级
    private int Class; //班级
    private int Math_Points; //数学成绩
    private int Chinese_Points; //语文成绩
    private int English_Points; //英语成绩

    public int Get_Math_Points() {return Math_Points;}
    public int Get_Chinese_Points() {return Chinese_Points;}
    public int Get_English_Points() {return English_Points;}
    public int Get_Grade() {return Grade;}
    public int Get_Class() {return Class;}

    public void Set_Grade(int g) {Grade=g;}
    public void Set_Class(int c) {Class=c;}
    public void Set_Math_Points(int scores) {Math_Points=scores;}
    public void Set_Chinese_Points(int scores) {Chinese_Points=scores;}
    public void Set_English_Points(int scores) {English_Points=scores;}
   // public Student(String n=null,int g=0,int c=0,int mp=0,int cp=0,int ep=0)
    public Student(String n,int g,int c,int mp,int cp,int ep) //java不支持默认参数！
    {
        super(n); //父类构造函数
        Grade=g; Class=c; Math_Points=mp; Chinese_Points=cp; English_Points=ep;
    }
    public Student()
    {
        super();
        Grade=Class=Math_Points=Chinese_Points=English_Points=0; //初始化
    }
    public void Edit_Points()
    {
        System.out.println("What subject do you want to edit?");
        Scanner in=new Scanner(System.in);
        String subject;
        subject=in.nextLine();
        int new_pts;
        System.out.println("Input the new score");
        new_pts=in.nextInt();
        if(subject.equals("Math")||subject.equals("math"))
        {
            Set_Math_Points(new_pts);
        }
        else if (subject.equals("Chinese")||subject.equals("chinese"))
        {
            Set_Chinese_Points(new_pts);
        }
        else if(subject.equals("English")||subject.equals("english"))
        {
            Set_English_Points(new_pts);
        }
        else
        {
            System.out.println("Wrong Input!"); return;
        }
        System.out.println("Succeed"); return;
    }
    public int sum() {return Chinese_Points+Math_Points+English_Points;}

}

abstract class  Employee extends Person  //抽象类
{
   // protected Teacher_Type type; //教师教学类型
    protected int Basic_Salary; //基本工资
    protected int Bonus; //绩效工资
    protected int Salary=0; //总工资

    public String Account; //登陆账号
    protected String Password; //登陆密码

    public Employee(String n,int bs,String acc,String pwd)
    {
        super(n);
        Basic_Salary=bs;
        Account=acc; Password=pwd;
    }
    public Employee()
    {
        super();
        Basic_Salary=0;
        Account= Password=null;
    }
    public int Get_Basic_Salary() {return Basic_Salary;}
    public void Set_Basic_Salary(int bs) {Basic_Salary=bs;}

    public abstract int Get_Sum_Salary();
    public abstract void Calculate_Bonus();
}

class Teacher extends Employee
{
    private Teacher_Type type; //教师教学科目类型
    private int Teaching_Time; //课时
    private boolean is_logined; //是否已经登陆
    private boolean is_locked=false; //是否因为多次登陆失败而被冻结
    private int failed_times=0; //登陆失败次数

    public Teacher(String n,String acc,String pwd,Teacher_Type tt,int time)
    {
        super(n,0,acc,pwd);
        type=tt; Teaching_Time=time;
    }
    public Teacher()
    {
        super();
        type=Teacher_Type.CHINESE; //默认为语文老师
        Teaching_Time=0;
    }

    public Teacher_Type Get_Type() {return type;}
    public void Set_Type(Teacher_Type t) {type=t;}

    public int Get_Teaching_Time() {return Teaching_Time;}
    public void Set_Teaching_Time(int tm) {Teaching_Time=tm;}

    public void Calculate_Bonus()
    {
        Bonus=Teaching_Time*200;
    }

    public int Get_Sum_Salary()
    {
        Calculate_Bonus();
        return Basic_Salary+Bonus;
    }
    
    public boolean Login(String acc,String pwd)
    {
        if(is_locked)
        {
            System.out.println("Your Account is locked!");
            return false;
        }
        if(acc.equals(Account)&&pwd.equals(Password))
        {
            is_logined=true;
            System.out.println("Login Succeed!");
            return true;
        }
        else
        {
            is_logined=false;
            System.out.println("Login Failed!");
            failed_times++;
            if(failed_times==5)
            {
                System.out.println("The Account is locked!");
                is_locked=true;
            }
            return false;
        }
    }
}
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
