package transfer.user.model;
import com.gs.fw.finder.Operation;
import java.util.*;
public class UserDetailsList extends UserDetailsListAbstract
{
	public UserDetailsList()
	{
		super();
	}

	public UserDetailsList(int initialSize)
	{
		super(initialSize);
	}

	public UserDetailsList(Collection c)
	{
		super(c);
	}

	public UserDetailsList(Operation operation)
	{
		super(operation);
	}
}
