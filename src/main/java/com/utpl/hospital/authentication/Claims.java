package com.utpl.hospital.authentication;

import java.lang.reflect.Array;

public class Claims {
    public ApplicationPermission[] PATIENT_CLAIMS=
            {ApplicationPermission.APPOINTMENT_READ,ApplicationPermission.DOCTOR_READ};
    public ApplicationPermission[] DOCTOR_CLAIMS=
            { ApplicationPermission.APPOINTMENT_WRITE};
}
