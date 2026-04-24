CREATE TABLE social_media (
    id UUID PRIMARY KEY,
    platform VARCHAR(50) NOT NULL,
    handle VARCHAR(255) NOT NULL,
    client_id UUID,
    professional_id UUID,
    created_at TIMESTAMP DEFAULT NOW(),

    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
    FOREIGN KEY (professional_id) REFERENCES professionals(id) ON DELETE CASCADE,
    CONSTRAINT check_social_media_owner CHECK (
        (client_id IS NOT NULL AND professional_id IS NULL) OR
        (client_id IS NULL AND professional_id IS NOT NULL)
    )
);
