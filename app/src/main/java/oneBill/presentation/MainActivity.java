package oneBill.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import cn.edu.tsinghua.cs.httpsoft.onebill.R;
import oneBill.control.Actioner;
import oneBill.domain.entity.error.DuplicationNameException;
import oneBill.domain.entity.error.NullException;

public class MainActivity extends AppCompatActivity {

    private Actioner actioner;
    private double addi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actioner=new Actioner(this);
//        try {
//            actioner.CreateBook("book1");
//            actioner.CreateMember("book1","Lillard");
//            actioner.CreateMember("book1","McColum");
//            actioner.CreateMember("book1","Aminu");
//            actioner.CreateMember("book1","Leonard");
//            actioner.CreateMember("book1","Pulumle");
//            actioner.CloseDataBase();
//        }catch (DuplicationNameException e) {
//            e.printStackTrace();
//        }
//        System.out.println(actioner.GetBook().get(0));
//        System.out.println(actioner.GetMember("book1").get(0));
//        actioner.CloseDataBase();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,AddRecordActivity.class);
                intent.putExtra("bookName","book1");
                startActivity(intent);
            }
        });

        //正式测试内容（请将以下代码复制到activity里进行测试，且请只运行一次！！！否则请把数据库删了重来……）

/*        Actioner actioner = new Actioner(this);
        try{
            //创建账本My Book1
            actioner.CreateBook("My Book1");
            //添加成员
            actioner.CreateMember("My Book1", "Andy");
            actioner.CreateMember("My Book1", "Ketty");
            actioner.CreateMember("My Book1", "Jack");
            actioner.CreateMember("My Book1", "Judy");
            //删除成员
            actioner.DeleteMember("My Book1", "Andy");
            //重设账本名
            actioner.SetName("My Book1", "New Name");
            //添加消费记录1
            ArrayList<Double> paid1 = new ArrayList<Double>();
            ArrayList<Double> payable1 = new ArrayList<Double>();
            paid1.add(12.26);
            paid1.add(10.0);
            paid1.add(5.0);
            payable1.add(20.0);
            payable1.add(7.26);
            payable1.add(0.0);
            actioner.CreateConsumRecord("New Name", 1, paid1, payable1);
            //添加消费记录2
            ArrayList<Double> paid2 = new ArrayList<Double>();
            ArrayList<Double> payable2 = new ArrayList<Double>();
            paid2.add(1.0);
            paid2.add(2.0);
            paid2.add(3.0);
            payable2.add(2.0);
            payable2.add(2.0);
            payable2.add(2.0);
            actioner.CreateConsumRecord("New Name", 1, paid2, payable2);
            //添加消费记录3
            ArrayList<Double> paid3 = new ArrayList<Double>();
            ArrayList<Double> payable3 = new ArrayList<Double>();
            paid3.add(1.0);
            paid3.add(2.0);
            paid3.add(3.0);
            payable3.add(2.0);
            payable3.add(2.0);
            payable3.add(2.0);
            actioner.CreateConsumRecord("New Name", 1, paid3, payable3);
            //删除消费记录3
            actioner.DeleteRecord(3);
            //添加借款记录4
            actioner.CreateLoanRecord("New Name", "Ketty", "Jack", 18.0);
            //创建账本My Book2
            actioner.CreateBook("My Book2");
            //添加成员
            actioner.CreateMember("My Book2", "Andy");
            actioner.CreateMember("My Book2", "Ketty");
            //关闭数据库（大家一定不要忘了这一步哇不然运行会报错的！）
            actioner.CloseDataBase();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
