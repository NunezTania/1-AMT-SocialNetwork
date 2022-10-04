import java.util.ArrayList;
import java.util.List;

public class Twitter implements IObservable {

    //region private attributes
    private List<IObserver> _observers = new ArrayList<IObserver>();
    private List<String> _twits = new ArrayList<String>();
    //endregion private attributes

    public Twitter(){}

    public Twitter(List<IObserver> observers){
        _observers.addAll(observers);
    }

    public List<IObserver> getObservers(){
        return new ArrayList<>(_observers);
    }

    public List<String> getTwits(){
        return new ArrayList<>(_twits);
    }

    public String lastTwit(){
        return _twits.get(_twits.size() - 1);
    }

    public void post(String twit){
        _twits.add(twit);
    }

    @Override
    public void subscribe(List<IObserver> observer) throws SubscriberAlreadyExistsException {
        boolean alreadyExists = false;
        if (observer != null)
        {
            for (IObserver obs : observer)
            {
                if (_observers.contains(obs))
                    alreadyExists = true;
                else
                    _observers.add(obs);
            }
        }
        if (alreadyExists)
            throw new SubscriberAlreadyExistsException();
    }

    @Override
    public void unsubscribe(IObserver observer) throws EmptyListOfSubscribersException, SubscriberNotFoundException {
        if (_observers.size() == 0)
            throw new EmptyListOfSubscribersException();
        if (!_observers.contains(observer))
            throw new SubscriberNotFoundException();
        _observers.remove(observer);
    }

    @Override
    public void notifyObservers() throws EmptyListOfSubscribersException {
        if (_observers.isEmpty())
            throw new EmptyListOfSubscribersException();
        for (IObserver observer : _observers)
        {
            observer.update(this);
        }
    }

    public class TwitterException extends Exception { }
    public class EmptyListOfSubscribersException extends TwitterException { }
    public class SubscriberAlreadyExistsException extends TwitterException { }
    public class SubscriberNotFoundException extends TwitterException { }
}
