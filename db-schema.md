# DB Schema:
```SQL
DB Schema:

Table Users {
  user_id integer [primary key]
  name varchar [not null]
  username varchar [unique, not null]
  user_email varchar [unique, not null]
  password_hash varchar [not null]
  created_at datetime [default: `now()`]
  updated_at datetime [default: `now()`]
  profile_pic varchar
  bio varchar
  last_login datetime
  activity_status varchar
  role_id integer [ref: > Roles.role_id]
  level_id integer [ref: > Levels.level_id]
}
Table Roles {
  role_id integer [primary key]
  role_name varchar [unique, not null]
  role_badge varchar  // freebie badge
  role_description varchar
  role_level integer  // for hierarchy
}
Table Socials {
  social_id integer [primary key]
  user_id integer [ref: > Users.user_id]
  github varchar  
  linkedin varchar
  portfolio varchar
}
Table Badges {
  badge_id integer [primary key]
  badge_name varchar
  badge_description varchar
  badge_img varchar
  badge_xp interger
  badge_tier varchar
  badge_category varchar
  created_at datetime [default: `now()`]
  updated_at datetime [default: `now()`]
}
Table UserBadges {
  badge_id integer [ref: > Badges.badge_id]
  user_id integer [ref: > Users.user_id]
  earned_at datetime [default: `now()`]
  status varchar [default: 'active'] // e.g., active, revoked
  comments varchar
  is_favorite boolean [default: false]
}
Table Stats {
  stat_id integer [primary key]
  user_id integer [ref: > Users.user_id]
  experience integer
  updated_at datetime [default: `now()`]
  badges_earned integer [default: 0]
  profile_views integer [default: 0]
}
Table Streaks {
  streak_id integer [primary key]
  user_id integer [ref: > Users.user_id]
  streak_type varchar [not null] // e.g., "login", "task_completion"
  current_streak integer [default: 0]
  longest_streak integer [default: 0]
  last_activity_date date
  updated_at datetime [default: `now()`]
}
Table Levels {
  level_id integer [primary key]  
  level_name varchar [not null]
  min_xp integer [not null]
  max_xp integer [not null]
  created_at datetime [default: `now()`]
  updated_at datetime [default: `now()`]
}
```


# MySQL(`8.4.4`) Table Creation
```SQL
CREATE TABLE roles (
    role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) UNIQUE NOT NULL,
    role_badge VARCHAR(255),
    role_description VARCHAR(255),
    role_level INTEGER
);

CREATE TABLE levels (
    level_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    level_name VARCHAR(255) NOT NULL,
    min_xp INTEGER NOT NULL,
    max_xp INTEGER NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE users (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    user_email VARCHAR(500) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    profile_pic VARCHAR(255),
    bio VARCHAR(255),
    last_login DATETIME,
    activity_status VARCHAR(255),
    role_id INTEGER,
    level_id INTEGER,
    FOREIGN KEY (role_id) REFERENCES Roles(role_id),
    FOREIGN KEY (level_id) REFERENCES Levels(level_id)
);

CREATE TABLE badges (
    badge_id INT PRIMARY KEY AUTO_INCREMENT,
    badge_name VARCHAR(255) NOT NULL,
    badge_description TEXT,
    badge_img VARCHAR(255),
    badge_xp INT NOT NULL,
    badge_tier VARCHAR(50),
    badge_category VARCHAR(50),
    created_at DATETIME DEFAULT NOW(),
    updated_at DATETIME DEFAULT NOW() ON UPDATE NOW()
);

CREATE TABLE awarded_badges (
    user_badge_id INT AUTO_INCREMENT PRIMARY KEY,
    badge_id INT NOT NULL,
    user_id INT NOT NULL,
    granted_by INT NOT NULL, -- User who granted the badge
    earned_at DATETIME DEFAULT NOW(),
    status VARCHAR(20) DEFAULT 'active',
    comments VARCHAR(255),
    is_favorite BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_userbadges_badge FOREIGN KEY (badge_id) REFERENCES Badges(badge_id) ON DELETE CASCADE,
    CONSTRAINT fk_userbadges_user FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_userbadges_granter FOREIGN KEY (granted_by) REFERENCES Users(user_id) ON DELETE SET CASCADE
);
```

# Default Badge Data
```SQL
INSERT INTO badges (badge_id, badge_name, badge_description, badge_img, badge_xp, badge_tier, badge_category, created_at, updated_at)
VALUES 
(6, 'Team Player', 'Recognizes exceptional collaboration and support for team members', 'https://0x0.st/8BC7.png', 100, 'BRONZE', 'CONTRIBUTION', '2025-03-03 00:46:19', NULL),
(7, 'Problem Solver', 'Awarded for finding creative solutions to difficult challenges', 'https://0x0.st/8Mrm.png', 150, 'SILVER', 'ACHIEVEMENT', '2025-03-03 00:48:03', '2025-03-03 00:55:05'),
(8, 'Innovation Award', 'Celebrates creative thinking and innovative approaches', 'https://0x0.st/8Msz.png', 200, 'GOLD', 'ACHIEVEMENT', '2025-03-03 00:56:29', NULL),
(9, 'Leadership', 'Recognizes outstanding leadership qualities and initiative', 'https://0x0.st/8MsP.png', 250, 'PLATINUM', 'ROLE', '2025-03-03 00:58:26', NULL),
(10, 'Excellence', 'Awarded for consistently exceeding expectations', 'https://0x0.st/8MsT.png', 300, 'DIAMOND', 'MILESTONE', '2025-03-03 00:59:29', NULL),
(11, 'Kindness', 'Celebrates acts of kindness and compassion in the workplace', 'https://0x0.st/8MsS.png', 120, 'BRONZE', 'CONTRIBUTION', '2025-03-03 01:00:12', NULL),
(12, 'Mentor', 'Awarded for guiding and mentoring others towards success', 'https://0x0.st/8Msj.png', 200, 'GOLD', 'ROLE', '2025-03-03 01:00:45', NULL),
(13, 'Hard Worker', 'Recognizes dedication, perseverance, and strong work ethic', 'https://0x0.st/8MsL.png', 180, 'SILVER', 'GENERIC', '2025-03-03 01:01:14', NULL),
(14, 'Fast Learner', 'Awarded for quickly adapting to new knowledge and skills', 'https://0x0.st/8Msf.png', 160, 'SILVER', 'ACHIEVEMENT', '2025-03-03 01:01:46', NULL),
(15, 'Customer Champion', 'Recognizes outstanding customer service and satisfaction', 'https://0x0.st/8MsW.png', 220, 'GOLD', 'PARTICIPATION', '2025-03-03 01:02:12', NULL);
```
