CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE professionals (
    id UUID PRIMARY KEY,
    user_id UUID UNIQUE NOT NULL,
    type VARCHAR(50) NOT NULL,
    registration_type VARCHAR(50) NOT NULL,
    registration_id VARCHAR(255) NOT NULL,
    specialty VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE students (
    id UUID PRIMARY KEY,
    user_id UUID UNIQUE NOT NULL,
    coach_id UUID,
    nutritionist_id UUID,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (coach_id) REFERENCES professionals(id),
    FOREIGN KEY (nutritionist_id) REFERENCES professionals(id)
);

CREATE TABLE biometrics (
    id UUID PRIMARY KEY,
    student_id UUID UNIQUE NOT NULL,
    date_of_birth TIMESTAMP NOT NULL,
    biological_sex VARCHAR(50) NOT NULL,
    menstrual_cycle_impact VARCHAR(255),

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE TABLE health_records (
    id UUID PRIMARY KEY,
    student_id UUID UNIQUE NOT NULL,
    health_issues TEXT,
    hypertension TEXT,
    diabetes TEXT,
    cardiac_issues TEXT,
    mental_health TEXT,
    steroid_use TEXT,
    daily_medication BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE TABLE medications (
    id UUID PRIMARY KEY,
    health_record_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    pathology VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW(),

    FOREIGN KEY (health_record_id) REFERENCES health_records(id) ON DELETE CASCADE
);

CREATE TABLE lifestyles (
    id UUID PRIMARY KEY,
    student_id UUID UNIQUE NOT NULL,
    stress_level VARCHAR(255),
    sleep_hours VARCHAR(255),
    diet_quality VARCHAR(255),
    alcohol_consumption VARCHAR(255),
    smoking VARCHAR(255),

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE TABLE physical_training (
    id UUID PRIMARY KEY,
    student_id UUID UNIQUE NOT NULL,
    activity_level INT NOT NULL,
    weekly_frequency VARCHAR(255),
    current_activities TEXT,
    goals TEXT,
    perceived_impact TEXT,
    body_comfort TEXT,
    exercise_restriction TEXT,
    restricted_exercise TEXT,
    preferred_shift VARCHAR(255),

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE TABLE payments (
    id UUID PRIMARY KEY,
    student_id UUID NOT NULL,
    professional_id UUID NOT NULL,
    service_type VARCHAR(50) NOT NULL,
    amount NUMERIC(10,2),
    due_date TIMESTAMP NOT NULL,
    payment_date TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT NOW(),

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (professional_id) REFERENCES professionals(id)
);

CREATE TABLE financial_config (
    id UUID PRIMARY KEY,
    student_id UUID UNIQUE NOT NULL,
    coach_due_date INT,
    nutritionist_due_date INT,

    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);