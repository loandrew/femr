/*
     fEMR - fast Electronic Medical Records
     Copyright (C) 2014  Team fEMR

     fEMR is free software: you can redistribute it and/or modify
     it under the terms of the GNU General Public License as published by
     the Free Software Foundation, either version 3 of the License, or
     (at your option) any later version.

     fEMR is distributed in the hope that it will be useful,
     but WITHOUT ANY WARRANTY; without even the implied warranty of
     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
     GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with fEMR.  If not, see <http://www.gnu.org/licenses/>. If
     you have any questions, contact <info@teamfemr.org>.
*/
package femr.business.services.core;

import femr.common.dtos.ServiceResponse;
import femr.common.models.*;
import femr.data.models.core.IPatientEncounter;

import java.util.List;

public interface IEncounterService {

    /**
     * Create a new patient encounter. Chief complaint sort order is the same as
     * the order they exist in the list.
     *
     * @param patientEncounterItem the new patient encounter, not null TODO: separate this into parameters.
     * @return a service response that contains a PatientEncounterItem representing the patient encounter that was created
     * and/or errors if they exist.
     */
    ServiceResponse<PatientEncounterItem> createPatientEncounter(PatientEncounterItem patientEncounterItem);

    /**
     * Checks a patient into medical by updating the time of their visit and the user who saw them.
     *
     * @param encounterId current encounter id, not null
     * @param userId      id of the physician, not null
     * @return a service response that contains a PatientEncounterItem representing the patient encounter that was updated
     * and/or errors if they exist.
     */
    ServiceResponse<PatientEncounterItem> checkPatientInToMedical(int encounterId, int userId);

    /**
     * Checks a patient into pharmacy by updating the time of their visit and the user who saw them.
     *
     * @param encounterId current encounter id, not null
     * @param userId      id of the pharmacist, not null
     * @return a service response that contains an IPatientEncounter representing the patient encounter that was updated
     * and/or errors if they exist. TODO: remove the data model here
     */
    ServiceResponse<IPatientEncounter> checkPatientInToPharmacy(int encounterId, int userId);

    /**
     * Retrieves the physician that saw a patient in medical.
     *
     * @param encounterId id of the encounter, not null
     * @return a service response that contains the user and/or errors if they exist.
     */
    ServiceResponse<UserItem> retrievePhysicianThatCheckedInPatientToMedical(int encounterId);

    /**
     * Creates a list of fields.
     *
     * @param tabFieldItems the fields, not null, required attributes:<ul><li>name</li><li>value</li></ul>
     * @param encounterId id of the current encounter, not null
     * @param userId id of the user saving the fields, not null
     * @return a service response that contains a list of TabFieldItems representing the fields that were created
     * and/or errors if they exist.
     */
    ServiceResponse<List<TabFieldItem>> createPatientEncounterTabFields(List<TabFieldItem> tabFieldItems, int encounterId, int userId);

    /**
     * Create a list of problems.
     *
     * @param problemValues each problem TODO: filter out empty/null values
     * @param encounterId id of the current encounter, not null
     * @param userId id of the user saving the problems, not null
     * @return a service response that contains a list of ProblemItems representing the problems that were created
     * and/or errors if they exist.
     */
    ServiceResponse<List<ProblemItem>> createProblems(List<String> problemValues, int encounterId, int userId);

    /**
     * Retrieves all problems.
     *
     * @param encounterId id of the encounter, not null
     * @return a service response that contains a list of ProblemItems that exist
     * and/or errors if they exist.
     */
    ServiceResponse<List<ProblemItem>> retrieveProblemItems(int encounterId);
}