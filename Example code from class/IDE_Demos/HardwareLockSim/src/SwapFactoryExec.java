import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SwapFactoryExec {
    public static void main( String args[] ) {
        HardwareData lock = new HardwareData( false );

        System.out.println("Starting Executor");
        ExecutorService threadExecutor = Executors.newCachedThreadPool();

        Thread[] worker = new Thread[5];
        for ( int i = 0; i < 5; i++ ) {
            worker[i] = new Thread(
                    new Worker2(String.format( "worker %d", i ), lock ));
            threadExecutor.execute(worker[i]);
        }

        // kick off thread execution under executor control
        threadExecutor.shutdown();
        System.out.println("Tasks started, main ends");
    }
}