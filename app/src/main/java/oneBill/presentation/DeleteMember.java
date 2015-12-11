package oneBill.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import oneBill.control.Actioner;
import cn.edu.tsinghua.cs.httpsoft.onebill.R;


public class DeleteMember extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<String> arr_adapter;
    private String withName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        //final Actioner actioner = new Actioner(this);

        Intent intent = getIntent();
        final String bookName = intent.getStringExtra("bookName");
        final String deleteName = intent.getStringExtra("deleteName");
        final Double deleteBill = intent.getDoubleExtra("deleteBill",-1);

        //数据
        //TODO 获取成员清单
        //ArrayList names = actioner.GetMember(bookName);
        ArrayList<String> names = new ArrayList<String>();
        names.add("张三");
        names.add("李四");
        names.add("王五");

        spinner = (Spinner) findViewById(R.id.personNameTo);

        ArrayList<String> data_List = new ArrayList();
        data_List.addAll(names);
        data_List.remove(deleteName);

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data_List);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                withName = parent.getItemAtPosition(position).toString();
                Toast.makeText(DeleteMember.this, "你点击的是：" + withName, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        TextView bookTitle = (TextView) findViewById(R.id.bookTitle);
        TextView personName = (TextView) findViewById(R.id.personName);
        TextView text = (TextView) findViewById(R.id.text);
        TextView bill = (TextView) findViewById(R.id.bill);
        TextView to = (TextView) findViewById(R.id.to);
        bookTitle.setText(bookName);
        personName.setText(deleteName);
        DecimalFormat df = new DecimalFormat("0.00");
        if (deleteBill > 0) {
            text.setText("脱离组织需要收清");
            bill.setText("￥ " + df.format(deleteBill));
        }
        else if (deleteBill < 0) {
            text.setText("脱离组织需要付清");
            bill.setText("￥ " + df.format(-deleteBill));
        }
        else {
            text.setText("没有应收/应付，可以正常脱离组织");
            bill.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            to.setVisibility(View.GONE);
        }

        Button deletePerson = (Button) findViewById(R.id.deleteButton);
        deletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 添加清帐函数
                //actioner.SettlePerson(bookName,deleteName,withName);
                // TODO: 添加跳转到账本首页
                //Intent intent = new Intent(DeleteMember.this, Book.class);
                //intent.putExtra("bookName",bookName);
                //startActivity(intent);
                Toast.makeText(DeleteMember.this, "Confirm delete the member "+withName, Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton goBack = (ImageButton) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMember.this.finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_member, menu);
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
