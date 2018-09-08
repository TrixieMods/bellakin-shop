package com.bellakin.shop.data.models.users;

import com.bellakin.core.data.actor.IActor;
import com.bellakin.core.data.actor.ISecuredUser;
import com.bellakin.core.data.model.Identifiable;

public interface IAppUser extends ISecuredUser, IActor, Identifiable {

  String getUsername();

  default String getName() {
    return getUsername();
  }

}
