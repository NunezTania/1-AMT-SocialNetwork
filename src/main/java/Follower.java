import java.util.List;

public class Follower implements IObserver {

    @Override
    public void update(IObservable observable) {
        Twitter twitter = (Twitter)observable;
        for (String twit : twitter.getTwits())
        {
            System.out.println(twit);
        }
    }
}
