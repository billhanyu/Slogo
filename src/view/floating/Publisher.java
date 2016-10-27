package view.floating;

public interface Publisher {

	void addSubscriber(Subscriber client);
	void notifySubscribers();
	
}
