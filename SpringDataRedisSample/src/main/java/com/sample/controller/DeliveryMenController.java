package com.sample.controller;

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
    @RequestMapping(value = "/checkState", method = RequestMethod.PUT)
    public ResponseEntity<State> checkState(@Valid @RequestBody Long package_id,@Valid @RequestBody State state) {
    	 ModelMapper modelMapper = new ModelMapper();
         Package package_ =authorizationService.findPackageById(package_id);
         package_= modelMapper.map(package_, Package.class);

         Boolean result =authorizationService.savePackage(DeliveryMen);
         if (result) {
             return ResponseEntity.ok("state is updated!!!");
         } else {
             return ResponseEntity.ok("An error occured!!!");
         }
    	 Boolean result =authorizationService.saveDeliveryMen(deliveryMen_);
         if (result) {
             return ResponseEntity.ok("deliveryMen is updated!!!");
         } else {
             return ResponseEntity.ok("An error occured!!!");
         }
    }

}
