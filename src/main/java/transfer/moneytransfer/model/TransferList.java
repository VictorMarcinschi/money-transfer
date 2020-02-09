package transfer.moneytransfer.model;
import com.gs.fw.finder.Operation;
import java.util.*;
public class TransferList extends TransferListAbstract
{
	public TransferList()
	{
		super();
	}

	public TransferList(int initialSize)
	{
		super(initialSize);
	}

	public TransferList(Collection c)
	{
		super(c);
	}

	public TransferList(Operation operation)
	{
		super(operation);
	}
}
