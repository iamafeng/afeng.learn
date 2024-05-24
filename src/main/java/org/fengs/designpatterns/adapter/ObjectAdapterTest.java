package org.fengs.designpatterns.adapter;

//对象适配器类
class ObjectAdapter implements Target
{
    private Adapter adapter;
    public ObjectAdapter(Adapter adapter)
    {
        this.adapter=adapter;
    }
    public void request()
    {
        adapter.specificRequest();
    }
}
//客户端代码
public class ObjectAdapterTest
{
    public static void main(String[] args)
    {
        System.out.println("对象适配器模式测试：");
        Adapter adapter = new Adapter();
        Target target = new ObjectAdapter(adapter);
        target.request();
    }
}