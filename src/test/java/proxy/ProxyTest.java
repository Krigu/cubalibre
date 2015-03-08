package proxy;

import com.thoughtworks.xstream.XStream;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import testclasses.Car;
import testclasses.CarFactory;
import testclasses.CarHolder;

import java.lang.reflect.Method;

@RunWith(EasyMockRunner.class)
public class ProxyTest {


    private String xml;

    private CarHolder createCarHolderMock() {
        CarHolder mockedCarHolder = EasyMock.createNiceMock(CarHolder.class);

        EasyMock.expect(mockedCarHolder.getCar()).andAnswer(new IAnswer<Car>() {
            @Override
            public Car answer() throws Throwable {
                return (Car) new XStream().fromXML(xml);
            }
        });

        EasyMock.replay(mockedCarHolder);

        return mockedCarHolder;
    }

    private CarHolder createCarHolderProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CarHolder.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = methodProxy.invokeSuper(o, objects);
                if (method.getName().startsWith("get")) {
                    xml = new XStream().toXML(result);
                }
                return result;
            }

        });
        return (CarHolder) enhancer.create();
    }

    @Test
    public void testProxy() {

        CarHolder carHolderProxy = createCarHolderProxy();
        CarHolder carHolderMock = createCarHolderMock();

        Car car = CarFactory.buildCarWithFourWheels();

        carHolderProxy.setCar(car);
        // Invoke getter of proxy
        carHolderProxy.getCar();

        Assert.assertEquals(car, carHolderMock.getCar());

    }


}
