package transfer.partner.model;
import com.gs.fw.finder.Operation;
import transfer.partner.model.ServicePartnerListAbstract;

import java.util.*;
public class ServicePartnerList extends ServicePartnerListAbstract
{
	public ServicePartnerList()
	{
		super();
	}

	public ServicePartnerList(int initialSize)
	{
		super(initialSize);
	}

	public ServicePartnerList(Collection c)
	{
		super(c);
	}

	public ServicePartnerList(Operation operation)
	{
		super(operation);
	}
}
