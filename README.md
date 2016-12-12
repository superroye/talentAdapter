# talentAdapter

定义了一个通用的RecyclerView.Adapter, 省去了每次使用RecyclerView，都要重定义一个RecyclerView.Adapter的麻烦。
代码量很少，有兴趣的同学可以自行修改。

使用方式很优雅，就两点：
1 定义holder继承TalentHolder，并实现相关类，设置资源ID。
2 初始化TalentAdapter。
  TalentAdapter adapter = new TalentAdapter();
  adapter.addHolderType(MyHolder.class);
  组件会根据MyHolder绑定的数据类型泛型找到对应的holder，注意不要重复。
3 增加了简单的注解支持。  
  HolderRes(R.layout.item)资源id直接绑定holder。
  AutoView 可以让你省去findById的时间。
 
 DEMO如下：
 
 public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        TalentAdapter adapter = new TalentAdapter();
        adapter.addHolderType(MyHolder.class);
        adapter.addHolderType(MyHolder1.class);

        recyclerView.setAdapter(adapter);
        List list = new ArrayList();
        list.add(new HashMap());
        list.addAll(Arrays.asList("232222222", "11111111111111", "MMMMMMMMMM"));
        list.add(new HashMap());
        
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

    @HolderRes(R.layout.item1)
    public static class MyHolder1 extends TalentHolder<HashMap> {

        @AutoView
        TextView text;

        public MyHolder1(View itemView) {
            super(itemView);
        }

        @Override
        public void toView() {
            text.setTextColor(new Random().nextInt(0x00ffffff) | 0xff000000);
            text.setText("9999999999");
        }
    }
}
