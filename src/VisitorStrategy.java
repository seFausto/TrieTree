import java.util.*;

abstract class VisitorStrategy {
	abstract void setStrategy(Strategy s);
	abstract List<String> visitTree(TrieTree tree);
}
