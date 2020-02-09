package transfer.moneytransfer.model;
import com.gs.fw.finder.Operation;
import java.util.*;
public class RetrievalList extends RetrievalListAbstract
{
	public RetrievalList()
	{
		super();
	}

	public RetrievalList(int initialSize)
	{
		super(initialSize);
	}

	public RetrievalList(Collection c)
	{
		super(c);
	}

	public RetrievalList(Operation operation)
	{
		super(operation);
	}
}
