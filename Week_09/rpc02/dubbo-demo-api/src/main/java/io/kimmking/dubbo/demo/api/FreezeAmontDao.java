package io.kimmking.dubbo.demo.api;

public interface FreezeAmontDao {
    void freezeAmount(FreezeAmount freezeAmount);

    void updateFreeze(FreezeAmount freezeAmount);
}
