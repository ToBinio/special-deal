package tobinio.specialdeal;

import org.apache.commons.lang3.NotImplementedException;

public interface SpecialOffer {
    default boolean getIsSpecialOffer() {
        throw new NotImplementedException();
    }

    default void setIsSpecialOffer(boolean isSpecialOffer) {
        throw new NotImplementedException();
    }

    default int getLifeTime() {
        throw new NotImplementedException();
    }

    default void setLifeTime(int lifeTime) {
        throw new NotImplementedException();
    }
}
