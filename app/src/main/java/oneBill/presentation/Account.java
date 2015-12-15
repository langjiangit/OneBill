package oneBill.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import cn.edu.tsinghua.cs.httpsoft.onebill.R;
import oneBill.control.Actioner;

public class Account extends AppCompatActivity {
    Actioner actioner;
    TextView tvNameInAccount;
    TextView tvAccount;
    ImageView ivToMainFromAccount;
    ImageView ivAddLogFromAccount;
    ImageView ivToMemberFromAccount;
    ImageView ivToClearFromAccount;
    ImageView ivDeleteFromAccount;
    String name;
    ArrayList<ArrayList<String>> arraylist;
    ArrayList<String> arraylist1;
    int numAccount;
    LinearLayout linearAccount;
    Vector<TextView> vTV = new Vector<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        actioner = new Actioner(this);
        final Intent intent = getIntent();
        name = intent.getStringExtra("name");

        tvNameInAccount = (TextView) findViewById(R.id.tvNameInAccount);
        tvNameInAccount.setText(name);

        tvAccount = (TextView) findViewById(R.id.tvAccount);
        tvAccount.setText(Double.toString( actioner.GetSum(name) ));

        ivToMainFromAccount = (ImageView) findViewById(R.id.ivToMainFromAccount);
        ivToMainFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Account.this, MainActivity.class));
            }
        });

        ivAddLogFromAccount = (ImageView) findViewById(R.id.ivAddLogFromAccount);
        ivAddLogFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Account.this, AddRecordActivity.class);
                intent1.putExtra("bookName",intent.getStringExtra("name"));
                startActivity(intent1);
            }
        });

        ivToMemberFromAccount = (ImageView) findViewById(R.id.ivToMemberFromAccount);
        ivToMemberFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Account.this, ManageMember.class);
                intent1.putExtra("bookName",intent.getStringExtra("name"));
                startActivity(intent1);
            }
        });

        ivToClearFromAccount = (ImageView) findViewById(R.id.ivToClearFromAccount);
        ivToClearFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Account.this,AccountClear.class);
                intent1.putExtra("name", name);
                startActivity(intent1);
            }
        });

        ivDeleteFromAccount = (ImageView) findViewById(R.id.ivDeleteFromAccount);
        ivDeleteFromAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Account.this)
                        .setTitle("Alert!")
                        .setMessage("Are you sure to DELETE the book?")
                        .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actioner.DeleteBook(name);
                                startActivity(new Intent(Account.this, MainActivity.class));
                                Account.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });

        arraylist = actioner.GetRecord(name);   //获取记录
        numAccount = arraylist.size();
        linearAccount = (LinearLayout) findViewById(R.id.linearAccount);
        for(int i = 0;i < numAccount;++ i) {
            final int num = i;  //传给ClickListener的final参数
            vTV.add(i, new TextView(Account.this));
            vTV.get(i).setId(i);

            arraylist1 = arraylist.get(i);  //获取第i条记录

            StringBuilder sb = new StringBuilder();
            sb.append(arraylist1.get(1));
            sb.append("    ");
            sb.append(arraylist1.get(3));
            sb.append("                          ");
            sb.append(arraylist1.get(2));
            vTV.get(i).setText(sb);

            vTV.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> arrayList = arraylist.get(num);

                    Intent i = new Intent(Account.this, AccountLog.class);
                    Bundle b = new Bundle();

                    b.putString("name", name);
                    b.putInt("id", Integer.parseInt(arrayList.get(0)));

                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(arrayList.get(1));
                    sb1.append("   ￥");
                    sb1.append(arrayList.get(2));
                    sb1.append("    ");
                    sb1.append(arrayList.get(3));
                    b.putString("detail", String.valueOf(sb1));
                    i.putExtras(b);

                    startActivity(i);
                }
            });
            linearAccount.addView(vTV.get(i));
        }
    }
}
