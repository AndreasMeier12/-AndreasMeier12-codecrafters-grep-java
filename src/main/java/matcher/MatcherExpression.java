package matcher;

import java.util.List;

public class MatcherExpression {

    List<IMatcher> matchers;

    boolean startAnchor;

    boolean endAnchor;

    public MatcherExpression(List<IMatcher> matchers, boolean startAnchor, boolean endAnchor) {
        this.matchers = matchers;
        this.startAnchor = startAnchor;
        this.endAnchor = endAnchor;
    }

    public List<IMatcher> getMatchers(){
        return matchers;
    }

    public boolean isStartAnchored(){
        return startAnchor;
    }

    public boolean isEndAnchored(){
        return endAnchor;
    }

}
