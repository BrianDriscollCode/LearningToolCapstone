import { createClient } from '@supabase/supabase-js';

// Replace these with your Supabase project's URL and anon/public key
const supabaseUrl = process.env.VUE_APP_SUPABASE_URL;
const supabaseKey = process.env.VUE_APP_SUPABASE_KEY;
export const supabase = createClient(supabaseUrl, supabaseKey);
