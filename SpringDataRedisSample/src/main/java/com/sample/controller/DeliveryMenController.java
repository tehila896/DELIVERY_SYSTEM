package com.sample.controller;

import java.awt.Point;
import java.util.LinkedList;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sample.dal.Package.Package;
import com.sample.dal.Package.State;
import com.sample.dal.customer.Customer;
import com.sample.dal.deliveryMen.DeliveryMen;
import com.sample.service.AuthorizationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RestController
@RequestMapping("/api")
public class DeliveryMenController {

    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value = "/addDeliveryMen", method = RequestMethod.POST)
    public ResponseEntity<String> addDeliveryMen(@Valid @RequestBody DeliveryMen DeliveryMen) {
        ModelMapper modelMapper = new ModelMapper();
        DeliveryMen deliveryMen_ = modelMapper.map(DeliveryMen, DeliveryMen.class);
        if(authorizationService.findDeliveryMenById(deliveryMen_.getId())!=null)
       	 return ResponseEntity.ok("The deliveryMen is already in the database,you can update him!!!");
        Boolean result =authorizationService.saveDeliveryMen(deliveryMen_);
        if (result) {
            return ResponseEntity.ok("A new deliveryMen is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }
    @RequestMapping(value = "/updadateDeliveryMen", method = RequestMethod.PUT)
    public ResponseEntity<String> updadateDeliveryMen(@Valid @RequestBody DeliveryMen DeliveryMen) {
        ModelMapper modelMapper = new ModelMapper();
        DeliveryMen deliveryMen_ = modelMapper.map(DeliveryMen, DeliveryMen.class);
        Boolean result =authorizationService.saveDeliveryMen(deliveryMen_);
        if (result) {
            return ResponseEntity.ok("deliveryMen is updated!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }
    }
    @RequestMapping(value = "/updadatePosition", method = RequestMethod.PUT)
    public ResponseEntity<String> updadatePosition(@Valid @RequestBody long id,@Valid @RequestBody Point position) {
        ModelMapper modelMapper = new ModelMapper(); 
        DeliveryMen deliveryMen_ = authorizationService.findDeliveryMenById(id);
        deliveryMen_.setPosition(position);
        deliveryMen_=modelMapper.map(deliveryMen_, DeliveryMen.class);
        Boolean result =authorizationService.saveDeliveryMen(deliveryMen_);
        if (result) {
            return ResponseEntity.ok("postion is updated!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }
    }
    
    @RequestMapping(value = "/updadateStatos", method = RequestMethod.PUT)
    public ResponseEntity<String> updadateStatosPackage(@Valid @RequestBody Long id,@Valid @RequestBody Boolean statosCompletePackage) {
        ModelMapper modelMapper = new ModelMapper(); 
        Package package_ = authorizationService.findPackageById(id);
        package_.setStatosCompletedPackage(statosCompletePackage);
        package_=modelMapper.map(package_, Package.class);
        Boolean result =authorizationService.savePackage(package_);
        if (result) {
            return ResponseEntity.ok("statos completed package is updated!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }
    }

    @RequestMapping(value = "/approvePackage", method = RequestMethod.PUT)
    public ResponseEntity<String> updadatePosition(@Valid @RequestBody Long package_id,@Valid @RequestBody Long deliveryMen_id) {
        ModelMapper modelMapper = new ModelMapper(); 
        DeliveryMen deliveryMen_ = authorizationService.findDeliveryMenById(deliveryMen_id);
        deliveryMen_.setStatosCurrentlyWorking(true);
        deliveryMen_=modelMapper.map(deliveryMen_, DeliveryMen.class);
        Package package_ = authorizationService.findPackageById(package_id);
        package_.setDeliveryMen_id(deliveryMen_id);
        package_=modelMapper.map(package_, Package.class);
        Boolean result =authorizationService.saveDeliveryMen(deliveryMen_)&&authorizationService.savePackage(package_);
        if (result) {
            return ResponseEntity.ok("approvePackage succsesful!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }
    }
    
    @RequestMapping(value = "/updateState", method = RequestMethod.PUT)
    public ResponseEntity<String> updateState(@Valid @RequestBody Long package_id,@Valid @RequestBody State state) {
    	 ModelMapper modelMapper = new ModelMapper();
         Package package_ =authorizationService.findPackageById(package_id);
         LinkedList<State> list_state=package_.getList_states();
         list_state.add(state);
         package_.setList_states(list_state);
         package_= modelMapper.map(package_, Package.class);        
         Boolean result =authorizationService.savePackage(package_);
         if (result) {
             return ResponseEntity.ok("state is updated!!!");
         } else {
             return ResponseEntity.ok("An error occured!!!");
         }
    }

}
