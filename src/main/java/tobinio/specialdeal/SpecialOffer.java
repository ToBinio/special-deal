package tobinio.specialdeal;

import org.apache.commons.lang3.NotImplementedException;

public interface IsSpecialOffer {
    default boolean getIsSpecialOffer() {
        throw new NotImplementedException();
    }

    default void setIsSpecialOffer(boolean isSpecialOffer) {
        throw new NotImplementedException();
    }
}
