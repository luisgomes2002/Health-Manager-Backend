CREATE TYPE role AS ENUM (
    'ADMIN',
    'COACH',
    'NUTRITIONIST',
    'STUDENT',
    'TESTER'
);

CREATE TYPE registration_type AS ENUM (
    'CREF',
    'CRN'
);

CREATE TYPE payment_status AS ENUM (
    'PAID',
    'PENDING',
    'OVERDUE'
);

CREATE TYPE professional_type AS ENUM (
    'COACH',
    'NUTRITIONIST'
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role role NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE professional_profile (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID UNIQUE NOT NULL,
    registration_type registration_type NOT NULL,
    registration_id VARCHAR(255) NOT NULL,
    specialty VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_professional_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE student_profile (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID UNIQUE NOT NULL,
    coach_id UUID,
    nutritionist_id UUID,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_student_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_student_coach
        FOREIGN KEY (coach_id)
        REFERENCES users(id),

    CONSTRAINT fk_student_nutritionist
        FOREIGN KEY (nutritionist_id)
        REFERENCES users(id)
);

CREATE TABLE biometrics (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_profile_id UUID UNIQUE NOT NULL,
    date_of_birth TIMESTAMP NOT NULL,
    biological_sex VARCHAR(50) NOT NULL,
    menstrual_cycle_impact VARCHAR(255),

    CONSTRAINT fk_biometrics_student
        FOREIGN KEY (student_profile_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE
);

CREATE TABLE health_record (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_profile_id UUID UNIQUE NOT NULL,
    health_issues TEXT,
    hypertension TEXT,
    diabetes TEXT,
    cardiac_issues TEXT,
    mental_health TEXT,
    steroid_use TEXT,
    daily_medication BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_health_student
        FOREIGN KEY (student_profile_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE
);

CREATE TABLE medication (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    health_record_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    pathology VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_medication_health
        FOREIGN KEY (health_record_id)
        REFERENCES health_record(id)
        ON DELETE CASCADE
);

CREATE TABLE lifestyle (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_profile_id UUID UNIQUE NOT NULL,
    stress_level VARCHAR(255),
    sleep_hours VARCHAR(255),
    diet_quality VARCHAR(255),
    alcohol_consumption VARCHAR(255),
    smoking VARCHAR(255),

    CONSTRAINT fk_lifestyle_student
        FOREIGN KEY (student_profile_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE
);

CREATE TABLE physical_training (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_profile_id UUID UNIQUE NOT NULL,
    activity_level INT NOT NULL,
    weekly_frequency VARCHAR(255),
    current_activities TEXT,
    goals TEXT,
    perceived_impact TEXT,
    body_comfort TEXT,
    exercise_restriction TEXT,
    restricted_exercise TEXT,
    preferred_shift VARCHAR(255),

    CONSTRAINT fk_training_student
        FOREIGN KEY (student_profile_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE
);

CREATE TABLE financial_config (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_profile_id UUID UNIQUE NOT NULL,
    coach_due_date INT,
    nutritionist_due_date INT,

    CONSTRAINT fk_financial_student
        FOREIGN KEY (student_profile_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE
);

CREATE TABLE payment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    service_type professional_type NOT NULL,
    amount NUMERIC(10,2),
    due_date TIMESTAMP NOT NULL,
    payment_date TIMESTAMP,
    status payment_status DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_payment_student
        FOREIGN KEY (student_id)
        REFERENCES student_profile(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_payment_professional
        FOREIGN KEY (professional_id)
        REFERENCES users(id)
);