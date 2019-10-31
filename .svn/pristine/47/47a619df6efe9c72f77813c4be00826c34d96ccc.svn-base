package atshunhengli.com.listener;

import atshunhengli.com.service.IotCallbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IotCallbackService iotCallbackService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        ApplicationContext parent = applicationContext.getParent();
        if (parent == null) {
            new Thread(()->{
                while (true){
                        Map<String, Object> map = iotCallbackService.getMap();
                        if(map.size()>0){
                            try {
                                Thread.sleep(60000*60*24);
                                iotCallbackService.setMap(new HashMap<>());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                        }
//                        System.out.println(map);
                }
            }).start();
        }
    }
}
