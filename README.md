# talentAdapter

定义了一个通用Adapter，按数据类型绑定holder, 省去了每次使用RecyclerView，都要重定义一个RecyclerView.Adapter的麻烦。
代码量很少，有兴趣的同学可以自行修改。

使用方式很优雅：

- 定义holder继承TalentHolder，并实现相关类，设置资源ID。
- 初始化TalentAdapter。
  TalentAdapter adapter = new TalentAdapter();
  adapter.addHolderType(MyHolder.class);
  组件会根据MyHolder绑定的数据类型泛型找到对应的holder，
  如需要多个holder对应一个数据Item，请实现TalentItemType接口，并使用
  registerHolder(Class, int)方法注册。
- 增加了简单的注解支持。  
  HolderRes(R.layout.item)资源id直接绑定holder。
  AutoView 可以让你省去findById的时间。

 **DEMO如下：**

```java
 public class MainActivity extends AppCompatActivity {
   RecyclerView recyclerView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    adapter = new TalentAdapter();
    adapter.registerHolder(Holder1.class);
    adapter.registerHolder(Holder2.class, MultiItem2.TYPE_1);
    adapter.registerHolder(Holder3.class, MultiItem2.TYPE_2);
    adapter.registerHolder(Holder3.class, MultiItem2.TYPE_3);

    recyclerView.setAdapter(adapter);
    List list = new ArrayList<>();
    list.add(new Item1("Item"));
    list.add(new MultiItem2(MultiItem2.TYPE_1, "Item"));
    list.add(new Item1("Item"));
    list.add(new MultiItem2(MultiItem2.TYPE_2, "Item"));
    list.add(new Item1("Item"));
    list.add(new MultiItem2(MultiItem2.TYPE_2, "Item"));
    list.add(new MultiItem2(MultiItem2.TYPE_3, "Item"));

    adapter.resetItems(list);
}

 @HolderRes(R.layout.item)
public static class MyHolder extends TalentHolder<String> {

    @AutoView
    TextView text;

    public MyHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void toView() {
        text.setTextColor(new Random().nextInt(0x00ffffff) | 0xff000000);
        text.setText(itemValue);
    }
}

@HolderRes(R.layout.item_1)
public class Holder2 extends TalentHolder<MultiItem2> {
public Holder2(View itemView) {
    super(itemView);
}

@AutoView
TextView textView;

@Override
public void toView() {
    textView.setText("Holder2 "+itemValue.text);
}
}
} 
```
