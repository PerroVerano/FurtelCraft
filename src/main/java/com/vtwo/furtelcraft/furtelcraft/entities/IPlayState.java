package com.vtwo.furtelcraft.furtelcraft.entities;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public interface IPlayState {
    <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event);
}
