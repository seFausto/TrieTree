import java.util.*;

public class VisitorStrategyImplementation extends VisitorStrategy {

	Strategy strategy;

	@Override
	void setStrategy(Strategy s) {
		strategy = s;
	}

	@Override
	List<String> visitTree(TrieTree tree) {
		List<String> result = new ArrayList<String>();

		result = strategy.algorithm(tree);

		return result;
	}

}
