package com.app.repositories;

import org.apache.tomcat.jni.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by Marcin on 24.04.2016.
 */
public interface AddressRepository extends JpaRepository<Address, Serializable> {
}
