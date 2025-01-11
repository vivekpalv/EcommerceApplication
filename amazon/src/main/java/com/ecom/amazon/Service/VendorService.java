package com.ecom.amazon.Service;

import com.ecom.amazon.Authentication.AuthDTO.SignUpVendorDTO;
import com.ecom.amazon.CustomException.VendorException;
import com.ecom.amazon.Entity.Vendor;
import com.ecom.amazon.Repository.VendorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor createVendor(SignUpVendorDTO vendorDto) {
        try {
            Vendor vendor = new Vendor(vendorDto.getName(), vendorDto.getEmail(), vendorDto.getMobile(), vendorDto.getPassword());
            Vendor createdVendor = this.vendorRepository.save(vendor);
            return createdVendor;
        }catch (DataAccessException error){
            throw new VendorException("Vendor creation failed (may be application loose connectivity to database)", error);
        }catch (Exception error){
            throw new VendorException("An un-expected error occurred inside createVendor method", error);
        }
    }

    public Vendor currentVendor(){
        Optional<Vendor> vendorById = vendorRepository.findById(1l);
        return vendorById.get();
    }
}
