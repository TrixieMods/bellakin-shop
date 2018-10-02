package com.bellakin.botzzer.data.models.users;

import com.bellakin.core.data.actor.IActor;
import com.bellakin.core.data.actor.IEmailable;
import com.bellakin.core.data.actor.ISecuredUser;
import com.bellakin.core.data.model.Identifiable;

public interface IAppUser extends ISecuredUser, IActor, Identifiable, IEmailable {

  String getUsername();

  default String getName() {
    return getUsername();
  }

}
