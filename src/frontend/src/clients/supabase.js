import { createClient } from '@supabase/supabase-js';

// Replace these with your Supabase project's URL and anon/public key
const supabaseUrl = 'https://zekxydhvrvjvgcxmkgbl.supabase.co';
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpla3h5ZGh2cnZqdmdjeG1rZ2JsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIyMzcwOTUsImV4cCI6MjA0NzgxMzA5NX0.LMg9n2uBPrMA5UaUh0E4uayJjbk0sd6S8uqkdT2HGe0';
export const supabase = createClient(supabaseUrl, supabaseKey);
